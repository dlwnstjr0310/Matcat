package com.greedy.matcat.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
public class MemberDTO implements UserDetails {

    private int memberNo;
    private String memberId;
    private String memberPwd;
    private String memberName;
    private String memberEmail;
    private char memberGender;
    private String memberPhone;
    private String memberAddress;
    private char memberLeaveYN;
    private String memberGrade;
    private List<AuthMemberDTO> memberRoleList;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authority = new HashSet<>();
        for (AuthMemberDTO role : memberRoleList) {
            authority.add(new SimpleGrantedAuthority(role.getAuthority().getAuthName()));
        }
        return authority;
    }

    @Override
    public String getPassword() {
        return memberPwd;
    }

    @Override
    public String getUsername() {
        return memberId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
