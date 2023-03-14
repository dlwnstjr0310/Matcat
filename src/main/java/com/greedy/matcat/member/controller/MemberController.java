package com.greedy.matcat.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/login")
    public void login() {
    }

    @GetMapping("/regist1")
    public void regist1() {
    }

    @GetMapping("/regist2")
    public void regist2(){}

    @GetMapping("/findId")
    public void findId() {
    }

    @GetMapping("/findPWD")
    public void findPWD(){}
}
