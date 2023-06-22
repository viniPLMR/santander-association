package com.santander.demo.dto;

import com.santander.demo.controller.dto.request.ProductRequest;
import com.santander.demo.repository.model.Product;

import java.time.LocalDateTime;

public class ProductDtoTestTemplate {

    public static Product productCreditCard (){
        Product product = new Product();
        product.setCreateProductDate(LocalDateTime.now());
        product.setProductFatherId(0L);
        product.setId(1L);
        product.setName("Cartao de Credito");
        product.setActive(Boolean.TRUE);
        product.setDescription("cartao de credito com limite ativo");
        return product;
    }

    public static Product productDebit (){
        Product product = new Product();
        product.setCreateProductDate(LocalDateTime.now());
        product.setProductFatherId(0L);
        product.setId(2L);
        product.setName("Cartao de debito");
        product.setActive(Boolean.TRUE);
        product.setDescription("cartao de debiyo com saldo ativo");
        return product;
    }

    public static ProductRequest productRequestTestByProduct(Product product){
        ProductRequest productRequest = new ProductRequest();
        productRequest.setActive(product.getActive());
        productRequest.setProductFatherId(productRequest.getProductFatherId());
        productRequest.setDescription(productRequest.getDescription());
        productRequest.setName(productRequest.getName());
        return productRequest;
    }

}
