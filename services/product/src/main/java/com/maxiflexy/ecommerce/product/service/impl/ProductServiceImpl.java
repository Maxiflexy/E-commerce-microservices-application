package com.maxiflexy.ecommerce.product.service.impl;

import com.maxiflexy.ecommerce.product.dto.*;
import com.maxiflexy.ecommerce.product.exception.ProductPurchaseException;
import com.maxiflexy.ecommerce.product.repository.ProductRepository;
import com.maxiflexy.ecommerce.product.service.ProductMapper;
import com.maxiflexy.ecommerce.product.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    public Integer createProduct(ProductRequest productRequest){
        var product = mapper.toProduct(productRequest);
        return productRepository.save(product).getId();
    }

    @Override
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        var productIds = request
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        var storedProducts = productRepository.findAllByIdInOrderById(productIds);
        if(productIds.size() != storedProducts.size()){
            throw new ProductPurchaseException("One or more products does not exists");
        }

        var storedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        for(int i = 0 ; i < storedProducts.size(); i++){
            var product = storedProducts.get(i);
            var productRequest = storedRequest.get(i);

            if(product.getAvailableQuantity() < productRequest.quantity())
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " + productRequest.productId());

            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);

            purchasedProducts.add(mapper.toProductPurchaseResponse(product, productRequest.quantity()));

        }

        return purchasedProducts;
    }

    @Override
    public ProductResponse findById(Integer productId) {
        return productRepository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow( () -> new EntityNotFoundException("Product not found with ID:: " + productId));


    }

    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
