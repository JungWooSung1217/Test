package com.demo.apidemo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JWTAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    @Builder
    public JWTAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
