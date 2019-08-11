package com.demo.apidemo.service.user;

import com.demo.apidemo.entity.UserInfo;
import com.demo.apidemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DemoUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserInfo demoUserInfo = userRepository.findByUsername(username);
        if(demoUserInfo != null) {
            return User.builder()
                    .username(demoUserInfo.getUsername())
                    .password(demoUserInfo.getPassword())
                    .authorities("ROLE_USER")
                    .build();
        }

        throw new UsernameNotFoundException("아이디 또는 비밀번호가 없습니다.");
    }
}
