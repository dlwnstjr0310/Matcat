package com.greedy.matcat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/member/update", "/member/delete").hasRole("MEMBER")
                // 관리자만 사용 가능한 기능은 현재는 없음
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/member/login")
                .defaultSuccessUrl("/")
                .failureForwardUrl("/member/loginfail")
                /* Security 에서 기본값으로 받아들이는 ID 는 username, 비밀번호는 password로 되어있어서 변경했음. */
                .usernameParameter("memberId")			// 아이디 파라미터명 설정
                .passwordParameter("memberPwd")			// 패스워드 파라미터명 설정
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .deleteCookies("JSESSIONID")
                /* 로그아웃 이후 세션 전체 만료 여부 */
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/")
                // 따라서 인가 오류 처리는 생략하였음
                .and()
                .build();
    }
//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception{
//
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(authenticationService)
//                .passwordEncoder(passwordEncoder())
//                .and().build();
//    }


}



