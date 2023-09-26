package com.adrianlui.letsplay.requests;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class AddUserRequest {
    private String name;
    private String email;
    private String password;
    private String role;
}
