package com.adrianlui.letsplay.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;
    @NotNull(message = "username cannot be null.")
    @Size(message = "username must be between 3 to 20 characters.", min = 3, max = 20)
    private String username;
    @NotNull(message = "email cannot be null.")
    @Email(message = "invalid email pattern.")
    @Size(message = "email max length is 75 characters.", max = 75)
    private String email;
    @NotNull(message = "password cannot be null.")
    @Size(message = "password must be between 8 to 50 characters.", min = 8, max = 50)
    private String password;
    private Role role;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(role.name()));
//    }

}
