package com.demo.apidemo.controller;

import com.demo.apidemo.auth.util.CSVReaderUtil;
import com.demo.apidemo.dto.ApiResponse;
import com.demo.apidemo.dto.PredictRequest;
import com.demo.apidemo.entity.Institute;
import com.demo.apidemo.entity.SupportedAmount;
import com.demo.apidemo.service.amount.InstituteService;
import com.demo.apidemo.service.amount.SupportedAmountService;
import com.demo.apidemo.service.querydsl.QueryDslService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    SupportedAmountService supportedAmountService;

    @Autowired
    InstituteService instituteService;

    @Autowired
    QueryDslService queryDslService;

    @PostMapping(value="/save/csv")
    public ResponseEntity<?> create(@RequestParam("file") MultipartFile multipartFile) throws IOException {

        if(multipartFile.isEmpty())
            return new ResponseEntity(new ApiResponse("false", "File is empty"),
                HttpStatus.BAD_REQUEST);
        List<String[]> csvList = CSVReaderUtil.readAll(multipartFile);
        List<Institute> instituteList = new ArrayList<>();
        for(int i = 0; i < csvList.size(); i++) {
            if(i == 0) {
                for(int j = 2; j <= 10; j++ ) {
                    Institute institute = Institute
                            .builder()
                            .instituteName(csvList.get(i)[j].
                                    replaceAll("\\(억원\\)", "")
                                    .replaceAll("1\\)", ""))
                            .build();
                    if(!supportedAmountService.existByInstituteName(institute.getInstituteName())) {
                        supportedAmountService.saveInstitute(institute);
                        instituteList.add(institute);
                    }
                }
                if(instituteList.size() == 0) break;
            }else {
                int year = Integer.parseInt(csvList.get(i)[0]);
                int month = Integer.parseInt(csvList.get(i)[1]);
                for(int k=2; k<= 10; k++) {
                    SupportedAmount supportedAmount = SupportedAmount
                            .builder()
                            .year(year)
                            .month(month)
                            .money(Integer.parseInt(csvList.get(i)[k].replaceAll(",", "")))
                            .institute(instituteList.get(k-2))
                            .build();
                    supportedAmountService.saveSupportedAmount(supportedAmount);
                }
            }
        }

        return ResponseEntity.ok(new ApiResponse("success", "The file was saved successful"));
    }

    @GetMapping("/banklist")
    public ResponseEntity<?> bankList() {
        return ResponseEntity.ok(instituteService.findAll());
    }

    @GetMapping("/total/year")
    public ResponseEntity<?> getTotalByYear() {
        return ResponseEntity.ok(queryDslService.getTotalByYear());
    }

    @GetMapping("/max/year")
    public ResponseEntity<?> getMaxAverageYear() {
        return ResponseEntity.ok(queryDslService.getMaxAverageYear());
    }

    @GetMapping("/minmax/year")
    public ResponseEntity<?> getAverageMinMax() {
        String bankName = "외환은행";
        HashMap<String,Object> result = new HashMap<>();
        result.put("bank", bankName);
        result.put("support_amount", queryDslService.getAverageMinMax(bankName));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/predict/amount")
    public ResponseEntity<?> predictAmount(@RequestBody PredictRequest request) {
        return ResponseEntity.ok(supportedAmountService.predictAmount(request.getBankName(), request.getMonth()));
    }
}
