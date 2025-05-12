package com.example.mtb.service;

import com.example.mtb.dto.LoginRequest;
import com.example.mtb.dto.auth.AuthResponse;
import com.example.mtb.mapper.auth.AuthMapper;
import com.example.mtb.security.jwt.AuthenticatedTokenDetails;

public interface AuthService {

    AuthResponse login (LoginRequest loginRequest);

    AuthResponse refresh(AuthenticatedTokenDetails tokenDetails);
}
