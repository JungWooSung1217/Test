package com.demo.apidemo.repository;

import com.demo.apidemo.entity.Institute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituteRepository extends JpaRepository<Institute, Long> {
    boolean existsByinstituteName(String name);
}
