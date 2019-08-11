package com.demo.apidemo.jpa;

import com.demo.apidemo.entity.UserInfo;
import com.demo.apidemo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(BCryptPasswordEncoder.class)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void userSaveRepositoryTest() {
        Collection<GrantedAuthority> authorities =
                Stream.of("ROLE_USER")
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        UserInfo userInfo = UserInfo
                .builder()
                .username("test1")
                .password(passwordEncoder.encode("test1"))
                .authorities(authorities)
                .build();
        userRepository.save(userInfo);
        UserInfo savedUserInfo = userRepository.findByUsername(userInfo.getUsername());
        assertEquals(userInfo.getUsername(), savedUserInfo.getUsername());
        assertFalse(passwordEncoder.matches(userInfo.getPassword(), savedUserInfo.getPassword()));
    }
}
