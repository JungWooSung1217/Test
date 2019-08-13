package com.demo.apidemo.service.amount;

import com.demo.apidemo.dto.PredictResponse;
import com.demo.apidemo.entity.Institute;
import com.demo.apidemo.entity.SupportedAmount;
import com.demo.apidemo.repository.InstituteRepository;
import com.demo.apidemo.repository.SupportedAmountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public PredictResponse predictAmount(String bankName, int month) {
        List<SupportedAmount> findList = supportedAmountRepository
                .findByInstitute_InstituteNameAndMonth(bankName, month);
        return predict(bankName, month, findList);
    }

    private PredictResponse predict(String bankName, int month, List<SupportedAmount> inputList) {
        double initialAmount = inputList
                .stream()
                .limit(3)
                .mapToDouble(SupportedAmount::getMoney)
                .average()
                .getAsDouble();
        double alpha = 0.8;
        for(int i = 4; i < inputList.size(); i++) {
            long predictionAmount =
                    Math.round(initialAmount + alpha*(inputList.get(i).getMoney() - initialAmount));
            initialAmount = predictionAmount;
        }
        long predictionAmount =
                Math.round(initialAmount + alpha*(inputList.get(inputList.size()-1).getMoney() - initialAmount));

        return PredictResponse.builder()
                .bank(bankName)
                .amount((int)predictionAmount)
                .month(month)
                .year(2018)
                .build();

    }
}
