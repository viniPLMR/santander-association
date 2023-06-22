package com.santander.demo.dto;

import com.santander.demo.controller.dto.request.ProblemRequest;
import com.santander.demo.repository.model.Problem;

import java.time.LocalDateTime;

public class ProblemDtoTestTemplate {

    public static Problem problemCreditCard(){
        Problem problem = new Problem();
        problem.setCreateProblemDate(LocalDateTime.now());
        problem.setName("problem credit card");
        problem.setActive(Boolean.TRUE);
        problem.setId(1L);
        problem.setDescription("cartao dando recusado mesmo com limite disponivel");
        return problem;
    }

    public static Problem problemDebit(){
        Problem problem = new Problem();
        problem.setCreateProblemDate(LocalDateTime.now());
        problem.setName("problem debit card");
        problem.setActive(Boolean.TRUE);
        problem.setId(2L);
        problem.setDescription("cartao passando mesmo sem saldo");
        return problem;
    }


    public static ProblemRequest problemRequestTestByProblem(Problem problem){
        ProblemRequest problemRequest = new ProblemRequest();
        problemRequest.setName(problem.getName());
        problemRequest.setActive(problem.getActive());
        problemRequest.setDescription(problem.getDescription());
        return problemRequest;
    }

}
