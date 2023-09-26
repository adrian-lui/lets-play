package com.adrianlui.letsplay.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateRequest {
    private String name;
    private String email;
    private String password;
    private String role;
}
