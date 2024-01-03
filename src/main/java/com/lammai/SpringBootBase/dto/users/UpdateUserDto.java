package com.lammai.SpringBootBase.dto.users;

import com.lammai.SpringBootBase.common.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {
    @Email(message = "Email is invalid")
    @NotEmpty(message = "Email must not be empty")
    @NotNull(message = "Email must not be null")
    private String email;

    @NotEmpty(message = "Username must not be empty")
    @NotNull(message = "Username must not be null")
    private String username;
    private Role role;
}
