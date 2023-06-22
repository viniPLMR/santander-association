package com.santander.demo.service;


import com.santander.demo.controller.dto.request.ProductRequest;
import com.santander.demo.controller.dto.response.ItemCreatedResponse;
import com.santander.demo.controller.dto.response.ProductResponse;
import com.santander.demo.repository.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface ProductService {

    Page<Product> getProducts(Pageable pageable);

    ItemCreatedResponse cadastraProduct(ProductRequest productRequest);

    Product getProductById(Long id);

    ProductResponse deletarProduto(Long id);
}
