package com.santander.demo.service.impl;

import com.santander.demo.controller.dto.request.AssociationRequest;
import com.santander.demo.controller.dto.response.AssociationResponse;
import com.santander.demo.repository.AssociationRepository;
import com.santander.demo.repository.model.ProductProblem;
import com.santander.demo.service.AssociationService;
import com.santander.demo.service.ProblemService;
import com.santander.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssociationServiceImpl implements AssociationService {

    @Autowired
    AssociationRepository associationRepository;

    @Autowired
    ProblemService problemService;

    @Autowired
    ProductService productService;

    @Override
    @Cacheable("problemProductsCache")
    public Page<ProductProblem> getProblemProduct(Long productId, Pageable pageable) {
        return associationRepository.findByProductId(productId, pageable);
    }

    @Override
    public AssociationResponse cadastraProblemProduct(Long productId, AssociationRequest associationRequest) {
        ProductProblem associationProductProblem = new ProductProblem();
        associationProductProblem.setProblem(problemService.getProblemById(associationRequest.getProblemId()));
        associationProductProblem.setProduct(productService.getProductById(productId));
        associationProductProblem.setActive(Boolean.TRUE);
        associationProductProblem.setCreateProductProblemDate(LocalDateTime.now());
        ProductProblem savedAssociation = associationRepository.save(associationProductProblem);
        return AssociationResponse.builder().createDate(savedAssociation.getCreateProductProblemDate()).build();
    }

    @Override
    public void deletarProblemProduct(Long productId, Long problemId) {
        ProductProblem productProblem = associationRepository.findByProductIdAndProblemId(productId, problemId)
                                                             .orElseThrow(() -> new RuntimeException("Associação não encontrada"));
        associationRepository.delete(productProblem);
    }

}
