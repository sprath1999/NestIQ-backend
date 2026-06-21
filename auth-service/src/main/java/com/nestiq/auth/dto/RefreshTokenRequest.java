package com.nestiq.auth.dto;

import jakarta.validation.constraints.NotBlank;

public class RefreshTokenRequest {

    @NotBlank(message = "Refresh token is required")
    private String refreshToken;

    // Getter
    public String getRefreshToken() { return refreshToken; }

    // Setter
    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }
}