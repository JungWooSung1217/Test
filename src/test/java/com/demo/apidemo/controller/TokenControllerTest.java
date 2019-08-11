package com.demo.apidemo.controller;

import com.demo.apidemo.dto.AuthenticationRequest;
import com.demo.apidemo.dto.JWTAuthenticationResponse;
import com.demo.apidemo.dto.SignUpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.StringUtils;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TokenControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String idtoken;

    @Test
    public void testTokenController() throws Exception {
        testSignUp();
        testLogin();
        testRefreshToken();
    }

    private void testSignUp() throws Exception {
        // SignUp Test
        String requestSignUpBody = objectMapper.writeValueAsString(new SignUpRequest("test22", "test22"));
        mvc.perform(put("/auth/signup")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestSignUpBody))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.isSuccess").value("true"))
            .andExpect(jsonPath("$.message").value("Signup was successful"));

        // Duplicate User SignUp Test
        mvc.perform(put("/auth/signup")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestSignUpBody))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.isSuccess").value("false"))
                .andExpect(jsonPath("$.message").value("Username is already exist"));
    }

    private void testLogin() throws Exception {
        // Login Test
        String requestLoginBody = objectMapper.writeValueAsString(new AuthenticationRequest("test22", "test22"));
        MvcResult loginResult = mvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestLoginBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.accessToken").exists())
                .andExpect(jsonPath("$.tokenType").exists())
                .andExpect(jsonPath("$.tokenType").value("Bearer"))
                .andReturn();

        JWTAuthenticationResponse jwtAuthenticationResponse =
                objectMapper.readValue(loginResult.getResponse().getContentAsString(), JWTAuthenticationResponse.class);
        assertFalse(StringUtils.isEmpty(jwtAuthenticationResponse.getAccessToken()));
        assertEquals(jwtAuthenticationResponse.getTokenType(), "Bearer");
        idtoken = jwtAuthenticationResponse.getAccessToken();
    }

    private void testRefreshToken() throws Exception {
        mvc.perform(post("/refreshtoken")
                .accept(MediaType.APPLICATION_JSON)
                .content(idtoken)
                .header("Authorization","Bearer " + idtoken))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.accessToken").exists())
                .andExpect(jsonPath("$.tokenType").exists())
                .andExpect(jsonPath("$.tokenType").value("Bearer"));
    }
}
