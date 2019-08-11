package com.demo.apidemo.service.investment;

import com.demo.apidemo.entity.Institute;
import com.demo.apidemo.entity.Investment;
import com.demo.apidemo.repository.InstituteRepository;
import com.demo.apidemo.repository.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InvestmentService {

    @Autowired
    InstituteRepository instituteRepository;

    @Autowired
    InvestmentRepository investmentRepository;

    public void saveInstitute(Institute institute) {
        instituteRepository.save(institute);
    }

    public void saveInvestment(Investment investment) {
        investmentRepository.save(investment);
    }

    public boolean existByInstituteName(String name) {
        return instituteRepository.existsByinstituteName(name);
    }
}
