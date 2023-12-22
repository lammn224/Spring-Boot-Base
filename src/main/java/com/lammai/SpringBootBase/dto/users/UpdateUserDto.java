package com.lammai.SpringBootBase.dto.users;

import com.lammai.SpringBootBase.common.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {
    private String email;
    private String username;
    private Role role;
}
