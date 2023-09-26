package com.adrianlui.letsplay.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateProductRequest {
    private String id;
    private String name;
    private String description;
    private String userId;
    private double price;
}
