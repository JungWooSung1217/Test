package com.demo.apidemo.jpa;

import com.demo.apidemo.dto.MaxAverageResult;
import com.demo.apidemo.dto.MinMaxResult;
import com.demo.apidemo.dto.TotalByYearResult;
import com.demo.apidemo.entity.Institute;
import com.demo.apidemo.entity.SupportedAmount;
import com.demo.apidemo.repository.InstituteRepository;
import com.demo.apidemo.repository.SupportedAmountRepository;
import com.demo.apidemo.repository.QueryDslRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueryDslRepositoryTest {

    @Autowired
    QueryDslRepository queryDslRepository;

    @Autowired
    SupportedAmountRepository supportedAmountRepository;

    @Autowired
    InstituteRepository instituteRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void setup() {
        Institute institute1 = Institute.builder().instituteCode(1L).instituteName("외환은행").build();
        instituteRepository.save(institute1);
        SupportedAmount supportedAmount1 = SupportedAmount.builder().year(2004).month(1).money(1850).institute(institute1).build();
        SupportedAmount supportedAmount2 = SupportedAmount.builder().year(2004).month(2).money(1850).institute(institute1).build();
        SupportedAmount supportedAmount3 = SupportedAmount.builder().year(2004).month(3).money(1850).institute(institute1).build();
        SupportedAmount supportedAmount4 = SupportedAmount.builder().year(2004).month(4).money(1850).institute(institute1).build();
        SupportedAmount supportedAmount5 = SupportedAmount.builder().year(2004).month(5).money(1850).institute(institute1).build();
        SupportedAmount supportedAmount6 = SupportedAmount.builder().year(2004).month(6).money(1850).institute(institute1).build();
        SupportedAmount supportedAmount7 = SupportedAmount.builder().year(2004).month(7).money(1850).institute(institute1).build();
        SupportedAmount supportedAmount8 = SupportedAmount.builder().year(2004).month(8).money(1850).institute(institute1).build();
        SupportedAmount supportedAmount9 = SupportedAmount.builder().year(2004).month(9).money(1850).institute(institute1).build();
        SupportedAmount supportedAmount10 = SupportedAmount.builder().year(2004).month(10).money(1850).institute(institute1).build();
        SupportedAmount supportedAmount11 = SupportedAmount.builder().year(2004).month(11).money(1850).institute(institute1).build();
        SupportedAmount supportedAmount12 = SupportedAmount.builder().year(2004).month(12).money(1850).institute(institute1).build();
        supportedAmountRepository.save(supportedAmount1);
        supportedAmountRepository.save(supportedAmount2);
        supportedAmountRepository.save(supportedAmount3);
        supportedAmountRepository.save(supportedAmount4);
        supportedAmountRepository.save(supportedAmount5);
        supportedAmountRepository.save(supportedAmount6);
        supportedAmountRepository.save(supportedAmount7);
        supportedAmountRepository.save(supportedAmount8);
        supportedAmountRepository.save(supportedAmount9);
        supportedAmountRepository.save(supportedAmount10);
        supportedAmountRepository.save(supportedAmount11);
        supportedAmountRepository.save(supportedAmount12);
        supportedAmount1 = SupportedAmount.builder().year(2005).month(1).money(1050).institute(institute1).build();
        supportedAmount2 = SupportedAmount.builder().year(2005).month(2).money(1050).institute(institute1).build();
        supportedAmount3 = SupportedAmount.builder().year(2005).month(3).money(1050).institute(institute1).build();
        supportedAmount4 = SupportedAmount.builder().year(2005).month(4).money(1050).institute(institute1).build();
        supportedAmount5 = SupportedAmount.builder().year(2005).month(5).money(1050).institute(institute1).build();
        supportedAmount6 = SupportedAmount.builder().year(2005).month(6).money(1050).institute(institute1).build();
        supportedAmount7 = SupportedAmount.builder().year(2005).month(7).money(1050).institute(institute1).build();
        supportedAmount8 = SupportedAmount.builder().year(2005).month(8).money(1050).institute(institute1).build();
        supportedAmount9 = SupportedAmount.builder().year(2005).month(9).money(1050).institute(institute1).build();
        supportedAmount10 = SupportedAmount.builder().year(2005).month(10).money(1050).institute(institute1).build();
        supportedAmount11 = SupportedAmount.builder().year(2005).month(11).money(1050).institute(institute1).build();
        supportedAmount12 = SupportedAmount.builder().year(2005).month(12).money(1050).institute(institute1).build();
        supportedAmountRepository.save(supportedAmount1);
        supportedAmountRepository.save(supportedAmount2);
        supportedAmountRepository.save(supportedAmount3);
        supportedAmountRepository.save(supportedAmount4);
        supportedAmountRepository.save(supportedAmount5);
        supportedAmountRepository.save(supportedAmount6);
        supportedAmountRepository.save(supportedAmount7);
        supportedAmountRepository.save(supportedAmount8);
        supportedAmountRepository.save(supportedAmount9);
        supportedAmountRepository.save(supportedAmount10);
        supportedAmountRepository.save(supportedAmount11);
        supportedAmountRepository.save(supportedAmount12);
    }

    @Test
    public void getTotalByYearTest() throws JsonProcessingException {
        List<TotalByYearResult> result = queryDslRepository.getTotalByYear();
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getYear(), 2004);
        assertEquals(result.get(0).getInstituteName(),"외환은행");
        assertEquals(result.get(0).getAverageAmount(), 66600);
        assertEquals(result.get(1).getYear(), 2005);
        assertEquals(result.get(1).getInstituteName(),"외환은행");
        assertEquals(result.get(1).getAverageAmount(), 37800);
    }

    @Test
    public void getMaxAverageYear() throws JsonProcessingException {
        MaxAverageResult result = queryDslRepository.getMaxAverageYear();
        assertEquals(result.getYear(), 2004);
        assertEquals(result.getBank(), "외환은행");
    }

    @Test
    public void getAverageMinMax() throws JsonProcessingException {
        List<MinMaxResult> result = queryDslRepository.getAverageMinMax("외환은행");
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getBank(),"외환은행");
        assertEquals(result.get(0).getYear(), 2004);
        assertEquals(result.get(0).getAmount(),1850);
        assertEquals(result.get(1).getBank(),"외환은행");
        assertEquals(result.get(1).getYear(), 2005);
        assertEquals(result.get(1).getAmount(),1050);
    }
}
