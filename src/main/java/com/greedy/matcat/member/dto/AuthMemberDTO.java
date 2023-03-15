package com.greedy.matcat.member.dto;

import lombok.Data;

@Data
public class AuthMemberDTO {

    private int memberNo;
    private int authorityCode;
    private AuthorityDTO authority;

}
