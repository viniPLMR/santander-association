package com.santander.demo.dto;

import com.santander.demo.controller.dto.request.AssociationRequest;
import com.santander.demo.repository.model.ProductProblem;

import java.time.LocalDateTime;

import static com.santander.demo.dto.ProblemDtoTestTemplate.problemCreditCard;
import static com.santander.demo.dto.ProblemDtoTestTemplate.problemDebit;
import static com.santander.demo.dto.ProductDtoTestTemplate.productCreditCard;
import static com.santander.demo.dto.ProductDtoTestTemplate.productDebit;

public class AssociationDtoTestTemplate {

    public static ProductProblem productProblemCredit(){
        ProductProblem productProblem = new ProductProblem();
        productProblem.setCreateProductProblemDate(LocalDateTime.now());
        productProblem.setProduct(productCreditCard());
        productProblem.setProblem(problemCreditCard());
        productProblem.setActive(true);
        productProblem.setId(1L);
        return productProblem;
    }

    public static ProductProblem productProblemDebit(){
        ProductProblem productProblem = new ProductProblem();
        productProblem.setCreateProductProblemDate(LocalDateTime.now());
        productProblem.setProduct(productDebit());
        productProblem.setProblem(problemDebit());
        productProblem.setActive(true);
        productProblem.setId(2L);
        return productProblem;
    }

    public static AssociationRequest associationRequestTestTemplate(ProductProblem productProblem){
        return AssociationRequest.builder()
                .problemId(productProblem.getProblem().getId())
                .build();
    }

}
