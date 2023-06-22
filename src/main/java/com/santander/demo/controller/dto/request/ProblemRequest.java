package com.santander.demo.controller.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
@JsonDeserialize
public class ProblemRequest {
    private String name;
    private String description;
    private Boolean active;
}
