package com.demo.apidemo.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Institute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "institute_code")
    private Long instituteCode;

    @Column(name = "institute_name")
    private String instituteName;

    //@OneToMany(mappedBy = "investment")
    //List<Investment> invements = new ArrayList<>();

    @Builder
    public Institute(Long instituteCode, String instituteName) {
        this.instituteCode = instituteCode;
        this.instituteName = instituteName;
    }
}
