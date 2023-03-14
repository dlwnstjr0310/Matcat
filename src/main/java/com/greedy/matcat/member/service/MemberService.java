package com.greedy.matcat.member.service;

import com.greedy.matcat.member.dto.MailDTO;

public interface MemberService {

    MailDTO createMailAndChangePassword(String str);

    void mailSend(MailDTO mail);

    String getTempPassword();

    void updatePassword(String str, String str2);
}
