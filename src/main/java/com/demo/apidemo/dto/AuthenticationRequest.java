package com.demo.apidemo.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class AuthenticationRequest implements Serializable {
    private static final long serialVersionUID = -6323891144172604852L;

    private String username;
    private String password;

    @Builder
    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
