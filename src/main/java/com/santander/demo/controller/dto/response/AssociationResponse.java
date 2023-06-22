package com.santander.demo.controller.dto.response;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AssociationResponse {
    private LocalDateTime createDate;
}
