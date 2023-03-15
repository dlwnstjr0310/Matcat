package com.greedy.matcat.member.controller;

import com.greedy.matcat.member.dto.MailDTO;
import com.greedy.matcat.member.dto.MemberDTO;
import com.greedy.matcat.member.service.MemberService;
import com.greedy.matcat.member.service.mailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String from;
    private final mailService mailService;
    private final MemberService memberService;
    private final PasswordEncoder PE;
    private final MessageSourceAccessor messageSourceAccessor;

    @Autowired
    public MemberController(JavaMailSender javaMailSender,
                            mailService mailService,
                            MemberService memberService,
                            PasswordEncoder PE,
                            MessageSourceAccessor messageSourceAccessor) {
        this.javaMailSender = javaMailSender;
        this.mailService = mailService;
        this.memberService = memberService;
        this.PE = PE;
        this.messageSourceAccessor = messageSourceAccessor;
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

    @PostMapping("/idDupCheck")
    public ResponseEntity<String> idCheck(@RequestBody MemberDTO member) {

        String result = "사용 가능한 아이디입니다.";
        if (memberService.memberIdCheck(member.getMemberId())) {
            result = "중복 된 아이디가 존재합니다.";
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/regist")
    public String registMember(@ModelAttribute MemberDTO member,
                               @RequestParam String zipCode, @RequestParam String address1, @RequestParam String address2,
                               RedirectAttributes rttr) {

        log.info("gender : {} " , member);
        String Address = zipCode + "$" +address1 + "$"+address2;
        member.setMemberAddress(Address);
        PE.encode(member.getPassword());

        rttr.addFlashAttribute("message",messageSourceAccessor.getMessage("member.regist"));

        return "redirect:/member/success";
    }
    @GetMapping("/findId")
    public void findId() {

    }

    @GetMapping("/findPWD")
    public void findPWD() {
    }

    @PostMapping("hello")
    public String hello(@RequestParam(required = false) String Email) {
        MailDTO mail = mailService.createMailAndChangePassword(Email);
        mailService.mailSend(mail);
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
