package com.adrianlui.letsplay.domain.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateProductRequest {
    private String id;
    @NotNull(message = "product name cannot be null.")
    @Size(message = "product name must be between 1 to 50 characters.", min = 1, max = 50)
    private String name;
    @NotNull(message = "product description cannot be null.")
    @Size(message = "description must be between 3 to 200 characters.", min = 3, max = 200)
    private String description;
    @NotNull(message = "product price cannot be null.")
    @Min(message = "value cannot be less than 0.", value = 0)
    private Double price;
    @NotNull(message = "user id cannot be null.")
    private String userId;
}
