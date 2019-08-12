package com.demo.apidemo.repository;

import com.demo.apidemo.entity.SupportedAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupportedAmountRepository extends JpaRepository<SupportedAmount, Long> {

    //List<SupportedAmount> findByInstitute_institute_nameAndMonth(String instituteName, int month);
    List<SupportedAmount> findByInstitute_InstituteNameAndMonth(String instituteName, int month);
}
