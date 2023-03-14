package com.greedy.matcat.member.service;

import com.greedy.matcat.member.dao.MemberMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService{

    private final MemberMapper mapper;

    public MemberServiceImpl(MemberMapper memberMapper) {
        this.mapper = memberMapper;
    }

    @Override
    public boolean memberIdCheck(String memberId) {

        String result = mapper.memberIdCheck(memberId);

        return result != null ? true : false;
    }
}
