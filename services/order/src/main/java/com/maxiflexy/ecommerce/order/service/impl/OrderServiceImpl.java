package com.maxiflexy.ecommerce.order.service.impl;

import com.maxiflexy.ecommerce.order.customer.CustomerClient;
import com.maxiflexy.ecommerce.order.dto.OrderLineRequest;
import com.maxiflexy.ecommerce.order.dto.OrderRequest;
import com.maxiflexy.ecommerce.order.exception.BusinessException;
import com.maxiflexy.ecommerce.order.kafka.OrderConfirmation;
import com.maxiflexy.ecommerce.order.kafka.OrderProducer;
import com.maxiflexy.ecommerce.order.product.ProductClient;
import com.maxiflexy.ecommerce.order.product.PurchaseRequest;
import com.maxiflexy.ecommerce.order.repository.OrderRepository;
import com.maxiflexy.ecommerce.order.service.OrderLineService;
import com.maxiflexy.ecommerce.order.service.OrderMapper;
import com.maxiflexy.ecommerce.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    @Override
    public Integer createOrder(OrderRequest orderRequest) {
        // check if the customer exists using OpenFeign
        var customer = this.customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow( () -> new BusinessException("Cannot create order:: No Customer exists with the provided ID"));

        // purchase the product --> which means we are using the product-microservice using RestTemplate
        var purchasedProducts = productClient.purchaseProducts(orderRequest.products());

        // persist the order
        var order = orderRepository.save(mapper.toOrder(orderRequest));

        // persist the order lines
        for(PurchaseRequest purchaseRequest: orderRequest.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        // todo start the payment process

        // send the order confirmation to notification microservice (kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.amount(),
                        orderRequest.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );
        return order.getId();

    }
}
