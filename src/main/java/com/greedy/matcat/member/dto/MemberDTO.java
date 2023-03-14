package com.greedy.matcat.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberDTO {

    private int memberNo;
    private String memberId;
    private String memberPwd;
    private String memberName;
    private String Email;
    private char Gender;
    private String Phone;
    private String Address;
    private String Grade;
}
