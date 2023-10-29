package com.adrianlui.letsplay.domain.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddProductRequest {
    private String name;
    private String description;
    private String userId;
    private Double price;
}
