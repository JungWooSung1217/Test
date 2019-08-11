package com.demo.apidemo.controller;

import com.demo.apidemo.dto.SignUpRequest;
import com.demo.apidemo.dto.ApiResponse;
import com.demo.apidemo.entity.UserInfo;
import com.demo.apidemo.service.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class UserController {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PutMapping("/auth/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest request) {
        if(userInfoService.existsByUsername(request.getUsername())) {
            return new ResponseEntity(new ApiResponse("false", "Username is already exist"),
                    HttpStatus.BAD_REQUEST);
        }
        Collection<GrantedAuthority> authorities =
                Stream.of("ROLE_USER")
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        UserInfo userInfo = UserInfo.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .authorities(authorities)
                .build();
        userInfoService.save(userInfo);
        return ResponseEntity.ok(new ApiResponse("true", "Signup was successful"));
    }

}
