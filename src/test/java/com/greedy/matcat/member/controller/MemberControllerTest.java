package com.greedy.matcat.member.controller;

import com.greedy.matcat.MatcatApplication;
import com.greedy.matcat.member.dto.MemberDTO;
import com.greedy.matcat.member.service.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = {MatcatApplication.class})
class MemberControllerTest {

    @Autowired
    private MemberController memberController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @Test
    void 아이디중복체크테스트() throws Exception {
        //given

        //when & then
    }

    @Test
    void registMember() {
    }

    @Test
    void doubleCheckPWD() {
    }
}