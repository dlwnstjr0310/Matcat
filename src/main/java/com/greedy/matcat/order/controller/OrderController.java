package com.greedy.matcat.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class OrderController { // 주문서는 /form 

	/* 결제하기로 이동 */
	/* 카카오페이나 카드 결제 누르면 누른정보 화면 노출 필요함 */
	@GetMapping("/order")
	public String orderList(Model model) {
//		log.info("[OrderController] : {}", );
		return "order/orderForm";
	}
	
}