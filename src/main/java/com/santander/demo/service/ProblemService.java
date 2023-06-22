package com.santander.demo.service;


import com.santander.demo.controller.dto.request.ProblemRequest;
import com.santander.demo.controller.dto.response.ItemCreatedResponse;
import com.santander.demo.controller.dto.response.ProblemResponse;
import com.santander.demo.repository.model.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProblemService {

    Page<Problem> getProblem(Pageable pageable);

    Problem getProblemById(Long idProblem);

    ItemCreatedResponse cadastraProblem(ProblemRequest productRequest);

    ProblemResponse deletarProblem(Long idProblem);
}
