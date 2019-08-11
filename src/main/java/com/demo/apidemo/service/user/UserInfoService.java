package com.demo.apidemo.service.user;

import com.demo.apidemo.entity.UserInfo;
import com.demo.apidemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserInfoService {
    @Autowired
    UserRepository userRepository;

    public UserInfo findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public void save(UserInfo userInfo) {
        userRepository.save(userInfo);
    }
}
