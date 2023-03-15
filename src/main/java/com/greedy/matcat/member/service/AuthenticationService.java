package com.greedy.matcat.member.service;


import com.greedy.matcat.member.dao.MemberMapper;
import com.greedy.matcat.member.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class AuthenticationService implements UserDetailsService {

    private final MemberMapper mapper;

    public AuthenticationService(MemberMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {

        log.info("[AuthenticationService] memberId : {} ",memberId);
        MemberDTO member = mapper.login(memberId);
        log.info("[AuthenticationService] memberId : {} ", member);
        if(member == null){
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다");
        }

        return member;
    }
}
