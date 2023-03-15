package com.greedy.matcat.member.dao;

import com.greedy.matcat.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    String memberIdCheck(String memberId);
    String selectMemberByEmail(String Email);
    MemberDTO login(String memberId);
    int memberRegist(MemberDTO member);
    int memberAuth();
}
