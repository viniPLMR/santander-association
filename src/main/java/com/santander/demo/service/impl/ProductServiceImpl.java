package com.santander.demo.service.impl;


import com.santander.demo.controller.dto.request.ProductRequest;
import com.santander.demo.controller.dto.response.ItemCreatedResponse;
import com.santander.demo.controller.dto.response.ProductResponse;
import com.santander.demo.repository.ProductRepository;
import com.santander.demo.repository.model.Product;
import com.santander.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    @Cacheable("ProductsCache")
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public ItemCreatedResponse cadastraProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setActive(productRequest.getActive());
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setProductFatherId(productRequest.getProductFatherId());
        product.setCreateProductDate(LocalDateTime.now());
        Product savedProduct = productRepository.save(product);
        return ItemCreatedResponse.builder()
                .createDate(savedProduct.getCreateProductDate())
                .id(savedProduct.getId())
                .build();
    }

    @Override
    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("ProductId not found"));
    }

    @Override
    public ProductResponse deletarProduto(Long id) {
        Product product = productRepository.getReferenceById(id);
        product.setActive(false);
        Product savedProductDeleted = productRepository.save(product);
        return ProductResponse.builder()
                .name(savedProductDeleted.getName())
                .description(savedProductDeleted.getDescription())
                .active(savedProductDeleted.getActive())
                .productFatherId(savedProductDeleted.getProductFatherId())
                .build();
    }
}
