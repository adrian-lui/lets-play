package com.adrianlui.letsplay.responses;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private String userId;
    private Double price;
}
