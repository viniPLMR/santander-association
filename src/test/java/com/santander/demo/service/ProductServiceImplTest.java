package com.santander.demo.service;

import com.santander.demo.controller.dto.response.ItemCreatedResponse;
import com.santander.demo.controller.dto.response.ProductResponse;
import com.santander.demo.repository.ProductRepository;
import com.santander.demo.repository.model.Product;
import com.santander.demo.service.impl.ProductServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.santander.demo.dto.ProductDtoTestTemplate.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void testGetProduct() {
        when(productRepository.findAll(PageRequest.of(0, 10))).thenReturn(new PageImpl<>(Arrays.asList(productCreditCard(), productDebit())));
        Page<Product> result = productService.getProducts(PageRequest.of(0, 10));

        assertEquals(2, result.getTotalElements());
        verify(productRepository, times(1)).findAll(PageRequest.of(0, 10));
    }

    @Test
    public void testGetProductNotFound() {
        List<Product> productList = Arrays.asList();

        when(productRepository.findAll(PageRequest.of(0, 10))).thenReturn(new PageImpl<>(productList));

        Page<Product> result = productService.getProducts(PageRequest.of(0, 10));

        assertEquals(0, result.getTotalElements());
        verify(productRepository, times(1)).findAll(PageRequest.of(0, 10));
    }

    @Test
    public void testCadastraProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(productCreditCard());

        ItemCreatedResponse result = productService.cadastraProduct(productRequestTestByProduct(productCreditCard()));

        assertEquals(productCreditCard().getId(), result.getId());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void testGetProductById() {
        Long productId = productCreditCard().getId();
        Product product = productCreditCard();

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(productId);

        assertEquals(productId, result.getId());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    public void testGetProductByIdNotFound() {
        Long productId = productCreditCard().getId();

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> productService.getProductById(productId));

        assertEquals(thrown.getMessage(), "ProductId not found");
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    public void testDeletarProduto() {
        Long productId = productDebit().getId();
        Product product = productDebit();

        when(productRepository.getReferenceById(productId)).thenReturn(product);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductResponse result = productService.deletarProduto(productId);

        assertEquals(product.getName(), result.getName());
        assertEquals(product.getDescription(), result.getDescription());
        assertEquals(false, result.getActive());
        assertEquals(product.getProductFatherId(), result.getProductFatherId());
        verify(productRepository, times(1)).getReferenceById(productId);
        verify(productRepository, times(1)).save(any(Product.class));
    }
}
