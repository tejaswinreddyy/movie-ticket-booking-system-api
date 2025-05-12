package com.example.mtb.dto.auth;

import lombok.Builder;

import java.time.Instant;

@Builder
public record AuthResponse(
        String userId,
        String username,
        String email,
        String role,
        long accessExpiration,
        long refreshExpiration,
        String accessToken,
        String refreshToken
) {}
