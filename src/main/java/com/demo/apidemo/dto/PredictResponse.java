package com.demo.apidemo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PredictResponse {
    String bank;
    int year;
    int month;
    int amount;

    @Builder
    public PredictResponse(String bank, int year, int month, int amount) {
        this.bank = bank;
        this.year = year;
        this.month = month;
        this.amount = amount;
    }
}
