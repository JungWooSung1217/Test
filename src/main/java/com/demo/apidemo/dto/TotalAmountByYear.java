package com.demo.apidemo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TotalAmountByYear {
    int year;
    int totalAmount;
}
