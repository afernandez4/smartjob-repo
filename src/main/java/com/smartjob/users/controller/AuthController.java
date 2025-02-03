package com.smartjob.users.controller;

import com.smartjob.users.dto.LoginRequest;
import com.smartjob.users.dto.LoginResponse;
import com.smartjob.users.security.JwtGeneratorService;
import com.smartjob.users.service.impl.UserDetailsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtGeneratorService jwtGeneratorService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;

    public AuthController(JwtGeneratorService jwtGeneratorService,
                          AuthenticationManager authenticationManager,
                          UserDetailsServiceImpl userDetailsService
    ) {
        this.jwtGeneratorService = jwtGeneratorService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {

            final var userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            String token = jwtGeneratorService.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse("Invalid credentials"));
        }
    }
}
