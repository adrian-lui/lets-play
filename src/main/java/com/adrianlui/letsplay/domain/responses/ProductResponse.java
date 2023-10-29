package com.adrianlui.letsplay.domain.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private String userId;
    private Double price;
}
