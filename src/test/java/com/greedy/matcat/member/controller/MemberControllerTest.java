package com.greedy.matcat.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedy.matcat.MatcatApplication;
import com.greedy.matcat.member.dto.MemberDTO;
import com.greedy.matcat.member.service.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = {MatcatApplication.class})
@Slf4j
class MemberControllerTest {

    @Autowired
    private MemberController memberController;

    @Autowired
    ObjectMapper objectMapper;

    private MockMvc mockMvc;
    private MemberDTO member;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(memberController)
                .addFilters(new CharacterEncodingFilter("UTF-8",true)).build();
    }

    @Test
    void 아이디중복체크테스트() throws Exception {
        //given
        String id = "안녕 반가워요";
        member = new MemberDTO();
        member.setMemberId(id);

        //when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/member/idDupCheck")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(member)))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.content().contentType("application/json; charset=UTF-8"))
                                .andDo(MockMvcResultHandlers.print())
                                .andReturn();
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //then
        assertThat(response).isEqualTo("하하");
    }
    @Test
    void 아이디중복체크실패테스트() throws Exception {
        //given
        String id = "admin";
        member = new MemberDTO();
        member.setMemberId(id);

        //when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/member/idDupCheck")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(member)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json; charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //then
        assertThat(response).isEqualTo("중복 된 아이디가 존재합니다.");
    }


    @Test
    void registMember() {
    }

    @Test
    void doubleCheckPWD() {
    }
}