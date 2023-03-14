package com.greedy.matcat.order.dto;

import java.sql.Date;

import com.greedy.matcat.member.dto.MemberDTO;

import lombok.Data;

@Data
public class OrderDTO {
	
	private Long ordCode;		// 주문번호
	private Date ordDate;		// 주문일자
	private Long ordPrice;		// 주문금액
	private MemberDTO member;	// 회원DTO조인시
	private Long ordTotalCount;	// 주문수량
	
}
