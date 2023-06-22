package com.santander.demo.controller.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
@JsonDeserialize
public class ProductRequest {
    private String name;
    private String description;
    private Long productFatherId;
    private Boolean active;
}
