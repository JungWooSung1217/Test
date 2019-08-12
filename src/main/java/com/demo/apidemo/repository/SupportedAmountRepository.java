package com.demo.apidemo.repository;

import com.demo.apidemo.entity.SupportedAmount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportedAmountRepository extends JpaRepository<SupportedAmount, Long> {
}
