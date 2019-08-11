package com.demo.apidemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MaxAverageResult {
    int year;

    String bank;

    @JsonIgnore
    int average;

    @QueryProjection
    public MaxAverageResult(int year, String bank, int average) {
        this.year = year;
        this.bank = bank;
        this.average = average;
    }
}
