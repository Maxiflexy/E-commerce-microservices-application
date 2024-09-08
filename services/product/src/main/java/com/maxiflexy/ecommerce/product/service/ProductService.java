package com.maxiflexy.ecommerce.product.service;

import com.maxiflexy.ecommerce.product.dto.ProductPurchaseRequest;
import com.maxiflexy.ecommerce.product.dto.ProductPurchaseResponse;
import com.maxiflexy.ecommerce.product.dto.ProductRequest;
import com.maxiflexy.ecommerce.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    Integer createProduct(ProductRequest productRequest);
    List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request);

    ProductResponse findById(Integer productId);

    List<ProductResponse> findAll();
}
