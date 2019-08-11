package com.demo.apidemo.repository;

import com.demo.apidemo.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserInfo, Long> {

    UserInfo findByUsername(String username);

    boolean existsByUsername(String username);
}
