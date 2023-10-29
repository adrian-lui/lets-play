package com.adrianlui.letsplay.domain.requests;

import com.adrianlui.letsplay.domain.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateRequest {
    private String username;
    private String email;
    private String password;
    private Role role;
}
