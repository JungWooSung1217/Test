package com.demo.apidemo.jpa;

import com.demo.apidemo.entity.Institute;
import com.demo.apidemo.entity.Investment;
import com.demo.apidemo.repository.InstituteRepository;
import com.demo.apidemo.repository.InvestmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

@RunWith(SpringRunner.class)
@DataJpaTest
public class InvestmentRepositoryTest {

    @Autowired
    InstituteRepository instituteRepository;

    @Autowired
    InvestmentRepository investmentRepository;

    @Test
    public void investmentRepositoryTest() {
        Institute institute = Institute
                .builder()
                .instituteCode(1L)
                .instituteName("주택도시기금")
                .build();
        Investment investment = Investment
                .builder()
                .year(2004)
                .month(1)
                .money(1850)
                .institute(institute)
                .build();
        instituteRepository.save(institute);
        investmentRepository.save(investment);
        Institute findInstitute = instituteRepository.findById(institute.getInstituteCode())
                .orElseThrow(() -> new EntityNotFoundException(institute.getInstituteCode().toString()));
        Investment findInvestment = investmentRepository.findById(institute.getInstituteCode())
                .orElseThrow(() -> new EntityNotFoundException(investment.getId().toString()));
    }
}
