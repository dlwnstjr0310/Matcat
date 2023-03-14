package com.greedy.matcat.member.dao;

import com.greedy.matcat.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    String memberIdCheck(String ID);
    MemberDTO login(String memberId);
}
