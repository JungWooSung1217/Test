package com.demo.apidemo.service.querydsl;

import com.demo.apidemo.dto.MaxAverageResult;
import com.demo.apidemo.dto.MinMaxResult;
import com.demo.apidemo.dto.TotalAmountByYear;
import com.demo.apidemo.dto.TotalByYearResult;
import com.demo.apidemo.repository.QueryDslRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class QueryDslService {
    @Autowired
    QueryDslRepository queryDslRepository;

    public Map<String,Object> getTotalByYear() {
        Map<String, Object> totalByYearResult = new HashMap<>();
        List<TotalAmountByYear> resultTotal = queryDslRepository.getTotalAmountByYear();
        List<TotalByYearResult> resultDetail = queryDslRepository.getTotalByYear();
        Map<Integer, List<TotalByYearResult>> groupByYear = resultDetail
                .stream()
                .collect(
                        Collectors.groupingBy(TotalByYearResult::getYear)
                );
        List<Map<String,Object>> resultList = new ArrayList<>();
        for(TotalAmountByYear total : resultTotal) {
            HashMap<String,Object> result = new HashMap<>();
            result.put("year", total.getYear() + "년");
            result.put("total_amount", total.getTotalAmount());
            List<TotalByYearResult> detail = groupByYear.get(total.getYear());
            result.put("detail_amount", detail.stream().collect(
                    Collectors.toMap(TotalByYearResult::getInstituteName, TotalByYearResult::getAverageAmount)));
            resultList.add(result);
        }
        totalByYearResult.put("name", "주택금융 공급현황");
        totalByYearResult.put("resultList", resultList);
        return totalByYearResult;
    }

    public MaxAverageResult getMaxAverageYear() {
        return queryDslRepository.getMaxAverageYear();
    }

    public List<MinMaxResult> getAverageMinMax(String bankName) {
        return queryDslRepository.getAverageMinMax(bankName);
    }
}
