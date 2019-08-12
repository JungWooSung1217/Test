package com.demo.apidemo.controller;

import com.demo.apidemo.auth.jwt.JWTTokenProvider;
import com.demo.apidemo.dto.AuthenticationRequest;
import com.demo.apidemo.dto.JWTAuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTTokenProvider jwtTokenProvider;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        String token = jwtTokenProvider.createToken(request.getUsername());
        JWTAuthenticationResponse response = JWTAuthenticationResponse.builder()
                .accessToken(token)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken() {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String token = jwtTokenProvider.createToken(user.getUsername());
            JWTAuthenticationResponse response = JWTAuthenticationResponse.builder()
                    .accessToken(token)
                    .build();
            return ResponseEntity.ok(response);
    }
}
