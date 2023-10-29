package com.adrianlui.letsplay.domain.requests;

import com.adrianlui.letsplay.domain.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateRequest {
    @Size(message = "username must be between 3 to 20 characters.", min = 3, max = 20)
    private String username;
    @Email(message = "invalid email pattern.")
    @Size(message = "email max length is 75 characters.", max = 75)
    private String email;
    @Size(message = "password must be between 8 to 50 characters.", min = 8, max = 50)
    private String password;
    private Role role;
}
