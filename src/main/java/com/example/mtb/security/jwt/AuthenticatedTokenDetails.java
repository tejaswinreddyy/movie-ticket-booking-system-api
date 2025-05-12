package com.example.mtb.security.jwt;

import java.time.Instant;

public record AuthenticatedTokenDetails(
        String email,
        String role,
        Instant tokenExpiration,
        String currentToken
) {}
