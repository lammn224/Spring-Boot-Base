package com.lammai.SpringBootBase.dto.users;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String email;
    private String username;
    private String password;
}
