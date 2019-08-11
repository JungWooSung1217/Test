package com.demo.apidemo.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Builder
    public UserInfo(String username, String password, String name, Collection<GrantedAuthority> authorities) {
        this.username= username;
        this.password = password;
    }
}
