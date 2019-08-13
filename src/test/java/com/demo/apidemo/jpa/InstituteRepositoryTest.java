package com.demo.apidemo.jpa;

import com.demo.apidemo.entity.Institute;
import com.demo.apidemo.repository.InstituteRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class InstituteRepositoryTest {
    @Autowired
    InstituteRepository instituteRepository;

    @Before
    public void setup() {
        Institute institute1 = Institute
                .builder()
                .instituteCode(1L)
                .instituteName("주택도시기금")
                .build();
        instituteRepository.save(institute1);
        Institute institute2 = Institute
                .builder()
                .instituteCode(2L)
                .instituteName("외환은행")
                .build();
        instituteRepository.save(institute2);
        Institute institute3 = Institute
                .builder()
                .instituteCode(3L)
                .instituteName("국민은행")
                .build();
        instituteRepository.save(institute3);
    }

    @Test
    public void supportedAmountRepositoryTest() {

        List<Institute> findInstituteList = instituteRepository.findAll();
        assertEquals(findInstituteList.size(), 3);
        assertEquals(findInstituteList.get(0).getInstituteName(), "주택도시기금");
        assertEquals(findInstituteList.get(1).getInstituteName(), "외환은행");
        assertEquals(findInstituteList.get(2).getInstituteName(), "국민은행");
    }
}
