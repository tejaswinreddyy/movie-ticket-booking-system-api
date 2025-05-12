package com.example.mtb.controller;

import com.example.mtb.dto.LoginRequest;
import com.example.mtb.dto.auth.AuthResponse;
import com.example.mtb.security.jwt.AuthenticatedTokenDetails;
import com.example.mtb.service.AuthService;
import com.example.mtb.util.ResponseStructure;
import com.example.mtb.util.RestResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final RestResponseBuilder responseBuilder;

    @PostMapping("/login")
    public ResponseEntity<ResponseStructure<AuthResponse>> login(@RequestBody LoginRequest loginRequest){
        AuthResponse authResponse = authService.login(loginRequest);
        return responseBuilder.sucess(HttpStatus.OK, "Login Successful", authResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<ResponseStructure<AuthResponse>> refresh(HttpServletRequest request){
        AuthenticatedTokenDetails tokenDetails = (AuthenticatedTokenDetails) request.getAttribute("tokenDetails");
        log.debug("Authenticated token details, email:{}, role:{}", tokenDetails.email(), tokenDetails.role());
        AuthResponse authResponse = authService.refresh(tokenDetails);
        return responseBuilder.sucess(HttpStatus.OK, "Login Successful", authResponse);
    }


}
