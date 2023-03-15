package com.greedy.matcat.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {

	@GetMapping("/adminHelp001")
	public void adminHelp001() {}
	
	@GetMapping("adminHelp002")
	public void adminHelp002() {}
	
	@GetMapping("adminHelp003")
	public void adminHelp003() {}
	
}