package com.demo.apidemo.config.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories(basePackages = "com.demo.apidemo.repository")
@EnableTransactionManagement
@Configuration
public class JpaConfig {
}
