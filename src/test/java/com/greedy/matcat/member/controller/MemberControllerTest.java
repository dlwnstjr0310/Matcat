package com.greedy.matcat.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedy.matcat.MatcatApplication;
import com.greedy.matcat.member.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.filter.CharacterEncodingFilter;

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
    @Mock
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
        assertThat(response).isEqualTo("사용 가능한 아이디입니다.");
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
    void 회원가입() throws Exception {
        //given
        MultiValueMap<String, String> hoho = new LinkedMultiValueMap<>();
        hoho.add("memberName", "이준석");
        hoho.add("memberId", "hoho123");
        hoho.add("memberPwd", "asdf!");
        hoho.add("memberEmail", "asdf@naver.com");
        hoho.add("memberGender", "F");
        hoho.add("memberPhone", "010-1234-1234");
        hoho.add("zipCode", "12345");
        hoho.add("address1", "히히");
        hoho.add("address2", "하하");

        //when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/member/regist").params(hoho))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.flash().attributeCount(1))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/member/success"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void 이메일확인성공() throws Exception {
        //given
        String mail = "asdf@naver.com";
        member = new MemberDTO();
        member.setMemberEmail(mail);
        //when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/member/pwDupCheck")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(member)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json; charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //then
        assertThat(response).isEqualTo("이메일 정보가 일치합니다. 가입 시 등록하신 이메일로 임시 비밀번호를 전송합니다.");
    }

    @Test
    void 이메일확인실패() throws Exception {
        //given
        String mail = "gkgkgk@abc.com";
        member = new MemberDTO();
        member.setMemberEmail(mail);
        //when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/member/pwDupCheck")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(member)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json; charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //then
        assertThat(response).isEqualTo("이메일 정보가 일치하지 않습니다.");
    }


}