package com.santander.demo.service.impl;

import com.santander.demo.controller.dto.request.ProblemRequest;
import com.santander.demo.controller.dto.response.ItemCreatedResponse;
import com.santander.demo.controller.dto.response.ProblemResponse;
import com.santander.demo.repository.ProblemRepository;
import com.santander.demo.repository.model.Problem;
import com.santander.demo.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    ProblemRepository problemRepository;

    @Override
    @Cacheable("ProblemCache")
    public Page<Problem> getProblem(Pageable pageable) {
        return problemRepository.findAll(pageable);
    }

    @Override
    public Problem getProblemById(Long idProblem) {
        return problemRepository.findById(idProblem).orElseThrow(() -> new RuntimeException("ProblemId not found"));
    }

    @Override
    public ItemCreatedResponse cadastraProblem(ProblemRequest problemRequest) {
        Problem problem = new Problem();
        problem.setActive(true);
        problem.setCreateProblemDate(LocalDateTime.now());
        problem.setName(problemRequest.getName());
        Problem savedProblem = problemRepository.save(problem);
        return ItemCreatedResponse.builder()
                .createDate(savedProblem.getCreateProblemDate())
                .id(savedProblem.getId())
                .build();
    }

    @Override
    public ProblemResponse deletarProblem(Long idProblem) {
        Problem problemUpdate = getProblemById(idProblem);
        problemUpdate.setActive(Boolean.FALSE);
        Problem savedProblem = problemRepository.save(problemUpdate);
        return ProblemResponse.builder().name(savedProblem.getName()).description(savedProblem.getDescription()).build();
    }
}
