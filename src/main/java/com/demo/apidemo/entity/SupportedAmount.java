package com.demo.apidemo.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Entity
public class SupportedAmount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int year;

    private int month;

    private int money;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institute_code")
    private Institute institute;

    @Builder
    public SupportedAmount(int year, int month, int money, Institute institute) {
        this.year = year;
        this.month = month;
        this.money = money;
        this.institute = institute;
    }
}
