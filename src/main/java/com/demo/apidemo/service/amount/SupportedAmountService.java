package com.demo.apidemo.service.amount;

import com.demo.apidemo.entity.Institute;
import com.demo.apidemo.entity.SupportedAmount;
import com.demo.apidemo.repository.InstituteRepository;
import com.demo.apidemo.repository.SupportedAmountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SupportedAmountService {

    @Autowired
    InstituteRepository instituteRepository;

    @Autowired
    SupportedAmountRepository supportedAmountRepository;

    public void saveInstitute(Institute institute) {
        instituteRepository.save(institute);
    }

    public void saveSupportedAmount(SupportedAmount supportedAmount) {
        supportedAmountRepository.save(supportedAmount);
    }

    public boolean existByInstituteName(String name) {
        return instituteRepository.existsByinstituteName(name);
    }
}
