package com.santander.demo.controller;


import com.santander.demo.controller.dto.request.AssociationRequest;
import com.santander.demo.controller.dto.response.AssociationResponse;
import com.santander.demo.repository.model.ProductProblem;
import com.santander.demo.service.AssociationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products/{productId}/problems")
public class AssociationController {

    @Autowired
    private AssociationService associationService;

    @GetMapping
    public ResponseEntity<Page<ProductProblem>> listProblemsByProduct(@PathVariable(value = "productId") Long productId, Pageable pageable) {
        return new ResponseEntity<>(associationService.getProblemProduct(productId, pageable),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AssociationResponse> createNewAssociation(@PathVariable(value = "productId") Long productId, @RequestBody AssociationRequest associationRequest) {
        return new ResponseEntity<>(associationService.cadastraProblemProduct(productId, associationRequest), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{problemId}")
    public ResponseEntity deletarProblem(@PathVariable(value = "productId") Long productId, @PathVariable(value = "problemId") Long problemId) {
        associationService.deletarProblemProduct(productId, problemId);
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }


}
