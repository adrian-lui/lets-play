package com.adrianlui.letsplay.responses;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class UserResponse {
    @Id
    private String id;
    private String name;
    private String email;
    private String role;
}
