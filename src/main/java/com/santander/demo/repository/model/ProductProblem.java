package com.santander.demo.repository.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "problem_product")
@Getter
@Setter
public class ProductProblem {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "create_problem_product")
    private LocalDateTime createProductProblemDate;

    @Column(name = "is_active")
    private Boolean active;
}
