package com.lammai.SpringBootBase.dto.users;

import com.lammai.SpringBootBase.common.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
    private String email;
    private String password;
    private String username;
    private Role role;
}
