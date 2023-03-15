package com.greedy.matcat.member.service;

import com.greedy.matcat.member.dto.MemberDTO;

public interface MemberService {


    boolean memberIdCheck(String memberId);

    boolean selectMemberByEmail(String memberEmail);

    int memberRegist(MemberDTO member);
}
