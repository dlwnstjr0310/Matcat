package com.greedy.matcat.member.controller;

import com.greedy.matcat.member.dto.MailDTO;
import com.greedy.matcat.member.dto.MemberDTO;
import com.greedy.matcat.member.service.MailServiceImpl;
import com.greedy.matcat.member.service.MemberService;
import com.greedy.matcat.member.service.MailService;
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
    private final MailServiceImpl mailService;
    private final MemberService memberService;
    private final PasswordEncoder PE;
    private final MessageSourceAccessor messageSourceAccessor;

    @Autowired
    public MemberController(JavaMailSender javaMailSender,
                            MailServiceImpl mailService,
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


    @GetMapping("/success")
    public void success(){}

    @PostMapping("/success")
    public String PostSuccess() {
        return "member/success";
    }

    @GetMapping("/findId")
    public void findId() {

    }

    @GetMapping("/findPWD")
    public void findPWD() {
    }

    /* ?????? ?????? */
    @PostMapping("/regist")
    public String registMember(@ModelAttribute MemberDTO member,
                               @RequestParam String zipCode, @RequestParam String address1, @RequestParam String address2,
                               RedirectAttributes rttr) {

        String Address = zipCode + "$" +address1 + "$"+address2;
        member.setMemberAddress(Address);
        PE.encode(member.getPassword());
        log.info("[MemberController] registMember : {} " , member);
        int result = memberService.memberRegist(member);

        if(result > 0) {
            rttr.addFlashAttribute("message",messageSourceAccessor.getMessage("member.regist"));
            return "redirect:/member/success";
        } else {
            rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("regist.fail"));
            return "redirect:/member/fail";
        }

    }


    /* ????????? ?????? ?????? */
    @PostMapping(value="/idDupCheck", produces="application/json;charset=utf-8")
    public ResponseEntity<String> idCheck(@RequestBody MemberDTO member) {

        String result = "?????? ????????? ??????????????????.";
        if (memberService.memberIdCheck(member.getMemberId())) {
            result = "?????? ??? ???????????? ???????????????.";
        }

        return ResponseEntity.ok(result);
    }

    /* ???????????? ??????????????? ?????? */
    @PostMapping(value="/pwDupCheck", produces="application/json;charset=utf-8")
    public ResponseEntity<String> doubleCheckPWD(@RequestBody MemberDTO member) {
        log.info("member : {}", member);

        String result = "????????? ????????? ???????????? ????????????.";
        if(memberService.selectMemberByEmail(member.getMemberEmail())) {
            result = "????????? ????????? ???????????????. ?????? ??? ???????????? ???????????? ?????? ??????????????? ???????????????.";
        }

        return ResponseEntity.ok(result);
    }

    /* ???????????? ?????? ??? ????????? ????????? */
    @PostMapping("/hello")
    public String sendMail(@RequestParam(required = false) String memberEmail) {
        MailDTO mail = mailService.createMailAndChangePassword(memberEmail);
        mailService.mailSend(mail);
        log.info("hello : {} " , mail);
        return "/member/login";
    }

}
