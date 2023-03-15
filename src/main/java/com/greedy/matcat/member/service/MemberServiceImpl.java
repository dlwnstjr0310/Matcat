package com.greedy.matcat.member.service;

import com.greedy.matcat.member.dao.MemberMapper;
import com.greedy.matcat.member.dto.MemberDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService{

    private final MemberMapper mapper;

    public MemberServiceImpl(MemberMapper memberMapper) {
        this.mapper = memberMapper;
    }

    /* 아이디 중복확인 */
    @Override
    public boolean memberIdCheck(String memberId) {

        String result = mapper.memberIdCheck(memberId);

        return result != null ? true : false;
    }

    /* 비밀번호 찾기 시 이메일 인증 */
    @Override
    public boolean selectMemberByEmail(String memberEmail) {

        String result = mapper.selectMemberByEmail(memberEmail);

        return result != null ? true: false;
    }

    @Override
    public int memberRegist(MemberDTO member) {

        int result = mapper.memberRegist(member);
        int result2 = mapper.memberAuth();

        if(result > 0 && result2 >0){
            return 1;
        }
        return 0;
    }

}
