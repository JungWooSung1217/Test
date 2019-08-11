package com.demo.apidemo.auth.jwt;


import org.springframework.security.core.AuthenticationException;

public class InvalidJwtAuthenticationException extends AuthenticationException {

    private static final long serialVersionUID = 8311220471501587010L;

    public InvalidJwtAuthenticationException(String e) {
        super(e);
    }
}
