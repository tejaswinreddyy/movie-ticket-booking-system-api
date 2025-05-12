package com.example.mtb.service.impl;

import com.example.mtb.config.AppEnv;
import com.example.mtb.dto.LoginRequest;
import com.example.mtb.dto.auth.AuthResponse;
import com.example.mtb.entity.UserDetails;
import com.example.mtb.enums.auth.TokenType;
import com.example.mtb.mapper.auth.AuthMapper;
import com.example.mtb.repository.UserRepository;
import com.example.mtb.security.SecurityConfig;
import com.example.mtb.security.jwt.AuthenticatedTokenDetails;
import com.example.mtb.security.jwt.JwtService;
import com.example.mtb.security.jwt.TokenPayLoad;
import com.example.mtb.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final SecurityConfig securityConfig;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthMapper authMapper;
    private final UserRepository userRepository;

    private final AppEnv env;


    @Override
    public AuthResponse login(LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());

        Authentication authentication = authenticationManager.authenticate(token);

        if (! authentication.isAuthenticated())
            throw new UsernameNotFoundException("Invalid Login Details");

        com.example.mtb.entity.UserDetails userDetails = userRepository.findByEmail(authentication.getName());

        TokenPayLoad access = tokenGenerator(userDetails, env.getToken().getAccessDuration() , TokenType.ACCESS);
        TokenPayLoad refresh = tokenGenerator(userDetails, env.getToken().getRefreshDuration(), TokenType.REFRESH );

        String accessToken = jwtService.createJwtToken(access);
        String refreshToken = jwtService.createJwtToken(refresh);

        return authMapper.authResponseMapper(userDetails,access, refresh, accessToken, refreshToken);
    }

    @Override
    public AuthResponse refresh(AuthenticatedTokenDetails tokenDetails) {

        UserDetails userDetails = userRepository.findByEmail(tokenDetails.email());

        TokenPayLoad access = tokenGenerator(userDetails, env.getToken().getAccessDuration(), TokenType.ACCESS);
        String accessToken = jwtService.createJwtToken(access);

        return new AuthResponse(
                userDetails.getUserId(),
                userDetails.getUsername(),
                tokenDetails.email(),
                tokenDetails.role(),
                access.expiration().toEpochMilli(),
                tokenDetails.tokenExpiration().toEpochMilli(),
                accessToken,
                tokenDetails.currentToken()
        );
    }

    private TokenPayLoad tokenGenerator(UserDetails userDetails, int minutesForExpiration, TokenType tokenType){
        Map<String, Object> claims = new HashMap<>();

            String role = userDetails.getUserRole().toString();
            claims.put("role", role);

        return new TokenPayLoad(
                claims,
                userDetails.getEmail(),
                Instant.now(),
                Instant.now().plusSeconds(minutesForExpiration* 60L),
                tokenType
        );
    }
}
