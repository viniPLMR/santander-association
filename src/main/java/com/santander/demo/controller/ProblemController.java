package com.santander.demo.controller;


import com.santander.demo.controller.dto.request.ProblemRequest;
import com.santander.demo.controller.dto.response.ItemCreatedResponse;
import com.santander.demo.repository.model.Problem;
import com.santander.demo.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @PostMapping
    public ResponseEntity<ItemCreatedResponse> createProblem(@RequestBody ProblemRequest problemRequest) {
        return new ResponseEntity<>(problemService.cadastraProblem(problemRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{problemId}")
    public ResponseEntity<Problem> getProblemById(@PathVariable(value = "problemId") Long idProblem) {
        return new ResponseEntity<>(problemService.getProblemById(idProblem), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Problem>> getProblem(Pageable pageable) {
        return new ResponseEntity<>(problemService.getProblem(pageable), HttpStatus.OK);
    }

    @PutMapping("/{problem_id}")
    public ResponseEntity deletarProblem(@PathVariable(value = "problemId") Long idProblem) {
        problemService.deletarProblem(idProblem);
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }

}
