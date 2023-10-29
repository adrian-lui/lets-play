package com.adrianlui.letsplay.domain.responses;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class UserResponse {
    @Id
    private String id;
    private String username;
    private String email;
    private String role;
}
