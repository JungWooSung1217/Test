package com.demo.apidemo.jpa;

import com.demo.apidemo.entity.Institute;
import com.demo.apidemo.entity.SupportedAmount;
import com.demo.apidemo.repository.InstituteRepository;
import com.demo.apidemo.repository.SupportedAmountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SupportedAmountRepositoryTest {

    @Autowired
    InstituteRepository instituteRepository;

    @Autowired
    SupportedAmountRepository supportedAmountRepository;

    @Test
    public void supportedAmountRepositoryTest() {
        Institute institute = Institute
                .builder()
                .instituteCode(1L)
                .instituteName("주택도시기금")
                .build();
        SupportedAmount supportedAmount = SupportedAmount
                .builder()
                .year(2004)
                .month(1)
                .money(1850)
                .institute(institute)
                .build();
        instituteRepository.save(institute);
        supportedAmountRepository.save(supportedAmount);
        Institute findInstitute = instituteRepository.findById(institute.getInstituteCode())
                .orElseThrow(() -> new EntityNotFoundException(institute.getInstituteCode().toString()));
        assertEquals(Optional.ofNullable(findInstitute.getInstituteCode()), Optional.of(1L));
        assertEquals(findInstitute.getInstituteName(), "주택도시기금");

        SupportedAmount findSupportedAmount = supportedAmountRepository.findById(institute.getInstituteCode())
                .orElseThrow(() -> new EntityNotFoundException(supportedAmount.getId().toString()));
        assertEquals(findSupportedAmount.getYear(), 2004);
        assertEquals(findSupportedAmount.getMonth(), 1);
        assertEquals(findSupportedAmount.getMoney(), 1850);
        assertEquals(Optional.ofNullable(findSupportedAmount.getInstitute().getInstituteCode()), Optional.of(1L));
        assertEquals(findSupportedAmount.getInstitute().getInstituteName(), "주택도시기금");

        List<SupportedAmount> findSuppoirtedAmountList =
                supportedAmountRepository.findByInstitute_InstituteNameAndMonth("주택도시기금", 1);
        assertEquals(findSuppoirtedAmountList.size(), 1);
        assertEquals(findSuppoirtedAmountList.get(0).getYear(), 2004);
        assertEquals(findSuppoirtedAmountList.get(0).getMonth(), 1);
        assertEquals(findSuppoirtedAmountList.get(0).getMoney(), 1850);
        assertEquals(Optional.ofNullable(findSuppoirtedAmountList.get(0).getInstitute().getInstituteCode()), Optional.of(1L));
        assertEquals(findSuppoirtedAmountList.get(0).getInstitute().getInstituteName(), "주택도시기금");
    }
}
