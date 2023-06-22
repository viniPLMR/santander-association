package com.santander.demo.controller.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductResponse {
    private String name;
    private String description;
    private Boolean active;
    private Long productFatherId;
}
