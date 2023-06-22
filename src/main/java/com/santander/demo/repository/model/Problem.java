package com.santander.demo.repository.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Problem {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private Boolean active;

    @Column(name = "create_problem_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createProblemDate;

}
