package com.lammai.SpringBootBase.dto.auth;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenDto {
    @NotEmpty(message = "Refresh token must not be empty")
    @NotNull(message = "Refresh token must not be null")
    private String refreshToken;
}
