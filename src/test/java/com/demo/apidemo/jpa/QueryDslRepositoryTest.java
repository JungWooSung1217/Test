package com.demo.apidemo.jpa;

import com.demo.apidemo.dto.MaxAverageResult;
import com.demo.apidemo.dto.MinMaxResult;
import com.demo.apidemo.dto.TotalByYearResult;
import com.demo.apidemo.entity.Institute;
import com.demo.apidemo.entity.Investment;
import com.demo.apidemo.repository.InstituteRepository;
import com.demo.apidemo.repository.InvestmentRepository;
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
    InvestmentRepository investmentRepository;

    @Autowired
    InstituteRepository instituteRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void setup() {
        Institute institute1 = Institute.builder().instituteCode(1L).instituteName("외환은행").build();
        instituteRepository.save(institute1);
        Investment investment1 = Investment.builder().year(2004).month(1).money(1850).institute(institute1).build();
        Investment investment2 = Investment.builder().year(2004).month(2).money(1850).institute(institute1).build();
        Investment investment3 = Investment.builder().year(2004).month(3).money(1850).institute(institute1).build();
        Investment investment4 = Investment.builder().year(2004).month(4).money(1850).institute(institute1).build();
        Investment investment5 = Investment.builder().year(2004).month(5).money(1850).institute(institute1).build();
        Investment investment6 = Investment.builder().year(2004).month(6).money(1850).institute(institute1).build();
        Investment investment7 = Investment.builder().year(2004).month(7).money(1850).institute(institute1).build();
        Investment investment8 = Investment.builder().year(2004).month(8).money(1850).institute(institute1).build();
        Investment investment9 = Investment.builder().year(2004).month(9).money(1850).institute(institute1).build();
        Investment investment10 = Investment.builder().year(2004).month(10).money(1850).institute(institute1).build();
        Investment investment11 = Investment.builder().year(2004).month(11).money(1850).institute(institute1).build();
        Investment investment12 = Investment.builder().year(2004).month(12).money(1850).institute(institute1).build();
        investmentRepository.save(investment1);
        investmentRepository.save(investment2);
        investmentRepository.save(investment3);
        investmentRepository.save(investment4);
        investmentRepository.save(investment5);
        investmentRepository.save(investment6);
        investmentRepository.save(investment7);
        investmentRepository.save(investment8);
        investmentRepository.save(investment9);
        investmentRepository.save(investment10);
        investmentRepository.save(investment11);
        investmentRepository.save(investment12);
        investment1 = Investment.builder().year(2005).month(1).money(1050).institute(institute1).build();
        investment2 = Investment.builder().year(2005).month(2).money(1050).institute(institute1).build();
        investment3 = Investment.builder().year(2005).month(3).money(1050).institute(institute1).build();
        investment4 = Investment.builder().year(2005).month(4).money(1050).institute(institute1).build();
        investment5 = Investment.builder().year(2005).month(5).money(1050).institute(institute1).build();
        investment6 = Investment.builder().year(2005).month(6).money(1050).institute(institute1).build();
        investment7 = Investment.builder().year(2005).month(7).money(1050).institute(institute1).build();
        investment8 = Investment.builder().year(2005).month(8).money(1050).institute(institute1).build();
        investment9 = Investment.builder().year(2005).month(9).money(1050).institute(institute1).build();
        investment10 = Investment.builder().year(2005).month(10).money(1050).institute(institute1).build();
        investment11 = Investment.builder().year(2005).month(11).money(1050).institute(institute1).build();
        investment12 = Investment.builder().year(2005).month(12).money(1050).institute(institute1).build();
        investmentRepository.save(investment1);
        investmentRepository.save(investment2);
        investmentRepository.save(investment3);
        investmentRepository.save(investment4);
        investmentRepository.save(investment5);
        investmentRepository.save(investment6);
        investmentRepository.save(investment7);
        investmentRepository.save(investment8);
        investmentRepository.save(investment9);
        investmentRepository.save(investment10);
        investmentRepository.save(investment11);
        investmentRepository.save(investment12);
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
