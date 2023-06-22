package com.santander.demo.service;


import com.santander.demo.controller.dto.request.AssociationRequest;
import com.santander.demo.controller.dto.response.AssociationResponse;
import com.santander.demo.dto.AssociationDtoTestTemplate;
import com.santander.demo.repository.AssociationRepository;
import com.santander.demo.repository.model.Problem;
import com.santander.demo.repository.model.Product;
import com.santander.demo.repository.model.ProductProblem;
import com.santander.demo.service.impl.AssociationServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.santander.demo.dto.AssociationDtoTestTemplate.*;
import static com.santander.demo.dto.ProblemDtoTestTemplate.problemCreditCard;
import static com.santander.demo.dto.ProductDtoTestTemplate.productCreditCard;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AssociationServiceImplTest {

    @Mock
    private AssociationRepository associationRepository;

    @Mock
    private ProblemService problemService;

    @Mock
    private ProductService productService;

    @InjectMocks
    private AssociationServiceImpl associationService;

    @Test
    public void testGetProblemProduct() {
        Long productId = 1L;
        List<ProductProblem> productProblemList = Arrays.asList(productProblemCredit());

        when(associationRepository.findByProductId(productId, PageRequest.of(0, 10))).thenReturn(new PageImpl<>(productProblemList));

        Page<ProductProblem> result = associationService.getProblemProduct(productId, PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
        verify(associationRepository, times(1)).findByProductId(productId, PageRequest.of(0, 10));
    }

    @Test
    public void testCadastraProblemProduct() {
        Long productId = 1L;
        Long problemId = 1L;
        AssociationRequest associationRequest = associationRequestTestTemplate(productProblemCredit());
        associationRequest.setProblemId(problemId);

        ProductProblem savedAssociation = productProblemCredit();

        when(problemService.getProblemById(problemId)).thenReturn(problemCreditCard());
        when(productService.getProductById(productId)).thenReturn(productCreditCard());
        when(associationRepository.save(any(ProductProblem.class))).thenReturn(savedAssociation);

        AssociationResponse result = associationService.cadastraProblemProduct(productId, associationRequest);

        assertEquals(savedAssociation.getCreateProductProblemDate(), result.getCreateDate());
        verify(problemService, times(1)).getProblemById(problemId);
        verify(productService, times(1)).getProductById(productId);
        verify(associationRepository, times(1)).save(any(ProductProblem.class));
    }



    @Test
    @DisplayName("neste caso de teste estoura o erro pelo produto nao existir")
    public void testCadastraProblemProductNotFoundProduct() {
        Long productId = 1L;
        Long problemId = 1L;
        AssociationRequest associationRequest = associationRequestTestTemplate(productProblemCredit());
        associationRequest.setProblemId(problemId);

        when(problemService.getProblemById(problemId)).thenReturn(problemCreditCard());
        when(productService.getProductById(productId)).thenThrow(new RuntimeException("ProductId not found"));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> associationService.cadastraProblemProduct(productId, associationRequest));

        assertEquals(thrown.getMessage(), "ProductId not found");
        verify(problemService, times(1)).getProblemById(problemId);
        verify(productService, times(1)).getProductById(productId);
    }

    @Test
    @DisplayName("neste caso de teste estoura o erro pelo problema nao existir")
    public void testCadastraProblemProductNotFoundProblem() {
        Long productId = 1L;
        Long problemId = 1L;
        AssociationRequest associationRequest = associationRequestTestTemplate(productProblemCredit());
        associationRequest.setProblemId(problemId);

        when(problemService.getProblemById(problemId)).thenThrow(new RuntimeException("ProblemId not found"));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> associationService.cadastraProblemProduct(productId, associationRequest));

        assertEquals(thrown.getMessage(), "ProblemId not found");
        verify(problemService, times(1)).getProblemById(problemId);
    }

    @Test
    public void testDeletarProblemProduct() {
        Long productId = 2L;
        Long problemId = 2L;
        ProductProblem productProblem = productProblemDebit();

        when(associationRepository.findByProductIdAndProblemId(productId, problemId)).thenReturn(Optional.of(productProblem));

        associationService.deletarProblemProduct(productId, problemId);

        verify(associationRepository, times(1)).findByProductIdAndProblemId(productId, problemId);
        verify(associationRepository, times(1)).delete(productProblem);
    }
}