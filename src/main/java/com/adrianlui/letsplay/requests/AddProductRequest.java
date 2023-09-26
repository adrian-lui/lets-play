package com.adrianlui.letsplay.requests;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class AddProductRequest {
    private String id;
    private String name;
    private String description;
    private String userId;
    private double price;
}
