package com.lammai.SpringBootBase.dto.auth;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @NotEmpty(message = "Password must not be empty")
    @NotNull(message = "Password must not be null")
    private String password;

    @NotEmpty(message = "Username must not be empty")
    @NotNull(message = "Username must not be null")
    private String username;
}
