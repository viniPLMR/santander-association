package com.santander.demo.controller.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProblemResponse {
    private String name;
    private String description;
    private Boolean active;
}
