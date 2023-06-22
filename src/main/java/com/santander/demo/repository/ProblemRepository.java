package com.santander.demo.repository;

import com.santander.demo.repository.model.Problem;
import com.santander.demo.repository.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {
}
