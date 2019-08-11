package com.demo.apidemo.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class SignUpRequest {
    String username;
    String password;

    @Builder
    public SignUpRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
