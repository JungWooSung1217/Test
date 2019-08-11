package com.demo.apidemo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TotalByYearResult {
    int year;
    String instituteName;
    int averageAmount;
}
