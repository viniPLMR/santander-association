package com.santander.demo.service;

import com.santander.demo.controller.dto.request.ProblemRequest;
import com.santander.demo.controller.dto.response.ItemCreatedResponse;
import com.santander.demo.controller.dto.response.ProblemResponse;
import com.santander.demo.dto.ProblemDtoTestTemplate;
import com.santander.demo.dto.ProductDtoTestTemplate;
import com.santander.demo.repository.ProblemRepository;
import com.santander.demo.repository.model.Problem;
import com.santander.demo.repository.model.Product;
import com.santander.demo.service.impl.ProblemServiceImpl;
import org.junit.Before;
import org.junit.Test;
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

import static com.santander.demo.dto.ProblemDtoTestTemplate.*;
import static com.santander.demo.dto.ProductDtoTestTemplate.productCreditCard;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProblemServiceImplTest {

    @Mock
    private ProblemRepository problemRepository;

    @InjectMocks
    private ProblemServiceImpl problemService;


    @Test
    public void testGetProblem() {
        List<Problem> problemList = Arrays.asList(problemDebit(), problemCreditCard());

        when(problemRepository.findAll(PageRequest.of(0, 10))).thenReturn(new PageImpl<>(problemList));

        Page<Problem> result = problemService.getProblem(PageRequest.of(0, 10));

        assertEquals(2, result.getTotalElements());
        verify(problemRepository, times(1)).findAll(PageRequest.of(0, 10));
    }

    @Test
    public void testGetProblemById() {
        Long problemId = problemDebit().getId();
        Problem problem = problemDebit();
        problem.setId(problemId);

        when(problemRepository.findById(problemId)).thenReturn(Optional.of(problem));

        Problem result = problemService.getProblemById(problemId);

        assertEquals(problemId, result.getId());
        verify(problemRepository, times(1)).findById(problemId);
    }

    @Test
    public void testGetProblemByIdNotFound() {
        Long productId = problemDebit().getId();

        when(problemRepository.findById(productId)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> problemService.getProblemById(productId));

        assertEquals(thrown.getMessage(), "ProblemId not found");
        verify(problemRepository, times(1)).findById(productId);
    }


    @Test
    public void testCadastraProblem() {
        ProblemRequest problemRequest = problemRequestTestByProblem(problemDebit());

        Problem savedProblem = problemDebit();

        when(problemRepository.save(any(Problem.class))).thenReturn(savedProblem);

        ItemCreatedResponse result = problemService.cadastraProblem(problemRequest);

        assertEquals(savedProblem.getId(), result.getId());
        assertEquals(problemRequest.getName(), savedProblem.getName());
        assertEquals(problemRequest.getActive(), savedProblem.getActive());
        verify(problemRepository, times(1)).save(any(Problem.class));
    }

    @Test
    public void testDeletarProblem() {
        Long problemId = problemCreditCard().getId();
        Problem problem = problemCreditCard();

        when(problemRepository.findById(problemId)).thenReturn(Optional.of(problem));
        when(problemRepository.save(any(Problem.class))).thenReturn(problem);

        ProblemResponse result = problemService.deletarProblem(problemId);

        assertEquals(problem.getName(), result.getName());
        assertEquals(false, problem.getActive());
        verify(problemRepository, times(1)).findById(problemId);
        verify(problemRepository, times(1)).save(any(Problem.class));
    }
}