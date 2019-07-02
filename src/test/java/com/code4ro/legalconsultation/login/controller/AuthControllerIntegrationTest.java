package com.code4ro.legalconsultation.login.controller;


import com.code4ro.legalconsultation.login.payload.LoginRequest;
import com.code4ro.legalconsultation.login.payload.SignUpRequest;
import com.code4ro.legalconsultation.login.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerIntegrationTest {
    private static final String USER_NAME = "userName";
    private static final String PASSWORD = "validPassword";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void signUp() throws Exception {
        final SignUpRequest signUpRequest = getSignUpRequest();
        String json = objectMapper.writeValueAsString(signUpRequest);

        // register successfuly
        mvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        assertThat(userRepository.existsByUsername(USER_NAME)).isNotNull();

        // fail to register with same username
        mvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{'message':'Duplicate username!'}"))
                .andExpect(status().isConflict());

        // fail to register with same email
        signUpRequest.setUsername("userName2");
        json = objectMapper.writeValueAsString(signUpRequest);
        mvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{'message':'Duplicate email!'}"))
                .andExpect(status().isConflict());
    }

    private SignUpRequest getSignUpRequest() {
        final SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setName(USER_NAME);
        signUpRequest.setUsername(USER_NAME);
        signUpRequest.setEmail("email@email.com");
        signUpRequest.setPassword(PASSWORD);
        return signUpRequest;
    }

    @Test
    public void login() throws Exception {
        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsernameOrEmail(USER_NAME);
        loginRequest.setPassword(PASSWORD);
        final String json = objectMapper.writeValueAsString(loginRequest);

        mvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
