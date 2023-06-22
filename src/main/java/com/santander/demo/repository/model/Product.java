package com.santander.demo.repository.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Product {


    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private Boolean active;

    private Long productFatherId;

    @Column(name = "create_product_date")
    private LocalDateTime createProductDate;

}
