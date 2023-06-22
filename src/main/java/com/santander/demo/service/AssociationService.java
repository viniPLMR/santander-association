package com.santander.demo.service;


import com.santander.demo.controller.dto.request.AssociationRequest;
import com.santander.demo.controller.dto.response.AssociationResponse;
import com.santander.demo.repository.model.ProductProblem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AssociationService {

    Page<ProductProblem> getProblemProduct(Long productId, Pageable pageable);

    AssociationResponse cadastraProblemProduct(Long productId, AssociationRequest productRequest);

    void deletarProblemProduct(Long productId, Long problemId);
}
