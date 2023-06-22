package com.santander.demo.controller;


import com.santander.demo.controller.dto.request.ProductRequest;
import com.santander.demo.controller.dto.response.ItemCreatedResponse;
import com.santander.demo.repository.model.Product;
import com.santander.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ItemCreatedResponse> createNewProduct(@RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productService.cadastraProduct(productRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> listByFilter(Pageable pageable) {
        return new ResponseEntity<>( productService.getProducts(pageable), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductsById(@PathVariable(value = "productId") Long productId) {
        return new ResponseEntity<>( productService.getProductById(productId), HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity updateProduct(@PathVariable(value = "productId") Long productId) {
        productService.deletarProduto(productId);
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }

}
