package com.demo.apidemo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString
@NoArgsConstructor
@Getter
@Entity
public class Investment {
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
    public Investment(int year, int month, int money, Institute institute) {
        this.year = year;
        this.month = month;
        this.money = money;
        this.institute = institute;
    }
}
