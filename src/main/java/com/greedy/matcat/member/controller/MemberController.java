package com.greedy.matcat.member.controller;

import com.greedy.matcat.member.dto.MailDTO;
import com.greedy.matcat.member.dto.MemberDTO;
import com.greedy.matcat.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String from;

    private final MemberService memberService;

    @Autowired
    public MemberController(JavaMailSender javaMailSender, MemberService memberService) {
        this.javaMailSender = javaMailSender;
        this.memberService = memberService;
    }


    @GetMapping("/login")
    public void login() {
    }

    @GetMapping("/regist1")
    public void regist1() {
    }

    @GetMapping("/regist2")
    public void regist2() {
    }

    @GetMapping("/findId")
    public void findId() {
    }

    @GetMapping("/findPWD")
    public void findPWD() {
    }

    @PostMapping("hello")
    public String hello(@RequestParam(required = false) String Email) {
        MailDTO mail = memberService.createMailAndChangePassword(Email);
        memberService.mailSend(mail);
        log.info("hello : {} " , mail);
        return "/member/login";
    }

    @PostMapping("/doubleCheck")
    public ResponseEntity<String> doubleCheckPWD(@RequestBody MemberDTO member) {

        String result = "이메일 정보가 일치합니다.";
        log.info("Memver info : {}", member);
//        if(memberService.selectMemberById(member.getMemberId())) {
//            result = "중복 된 아이디가 존재합니다.";
//        }

        return ResponseEntity.ok(result);
    }
}
