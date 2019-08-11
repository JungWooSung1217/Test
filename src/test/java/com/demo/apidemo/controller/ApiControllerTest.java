package com.demo.apidemo.controller;

import com.demo.apidemo.auth.jwt.JWTTokenProvider;
import com.demo.apidemo.entity.UserInfo;
import com.demo.apidemo.service.user.UserInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.InputStream;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ApiControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String idtoken;

    private final String username = "test";
    private final String password = "test";
    private final Collection<GrantedAuthority> authorities =
            Stream.of("ROLE_USER")
                   .map(SimpleGrantedAuthority::new)
                   .collect(Collectors.toList());

    @Before
    public void setup() {
        idtoken = jwtTokenProvider.createToken("test");
    }

    @Test
    public void apiTest() throws Exception {
        saveCsvTest();
        getTotalByYearTest();
        getMaxAverageYear();
        getAverageMinMax();
    }

    private void saveCsvTest() throws Exception {
        // 회원정보 저장
        userInfoService.save(UserInfo
                        .builder()
                        .username(username)
                        .password(passwordEncoder.encode(password))
                        .authorities(authorities)
                        .build()
        );

        //csv file upload
        InputStream inputStream = new ClassPathResource("csv/test.csv").getInputStream();
        MockMultipartFile csvFile = new MockMultipartFile(
                "file",
                "test.csv",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                inputStream);
            mvc.perform(multipart("/api/save/csv")
                .file(csvFile)
                .header("Authorization", "Bearer " + idtoken)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.isSuccess").value("success"))
                .andExpect(jsonPath("$.message").value("The file was saved successful"));
    }

    private void getTotalByYearTest() throws Exception {
        mvc.perform(get("/api/total/year")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer " + idtoken))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("주택금융 공급현황"))
                .andExpect(jsonPath("$.resultList", hasSize(13)));
    }

    private void getMaxAverageYear() throws Exception {
        mvc.perform(get("/api/max/year")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer " + idtoken))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.year").value(2017))
                .andExpect(jsonPath("$.bank").value("주택도시기금"));
    }

    private void getAverageMinMax() throws Exception {
        mvc.perform(get("/api/minmax/year")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer " + idtoken))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bank").value("외환은행"))
                .andExpect(jsonPath("$.support_amount", hasSize(2)))
                .andExpect(jsonPath("$.support_amount[0].bank").value("외환은행"))
                .andExpect(jsonPath("$.support_amount[0].year").value(2015))
                .andExpect(jsonPath("$.support_amount[0].amount").value(1702))
                .andExpect(jsonPath("$.support_amount[1].bank").value("외환은행"))
                .andExpect(jsonPath("$.support_amount[1].year").value(2017))
                .andExpect(jsonPath("$.support_amount[1].amount").value(0));
    }
}
