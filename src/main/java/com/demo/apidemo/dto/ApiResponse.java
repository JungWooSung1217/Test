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
public class ApiResponse {
    String isSuccess;
    String message;

    @Builder
    public ApiResponse(String isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }
}
