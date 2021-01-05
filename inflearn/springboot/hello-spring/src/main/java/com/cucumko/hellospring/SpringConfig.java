package com.cucumko.hellospring;

import com.cucumko.hellospring.repository.MemberRepository;
import com.cucumko.hellospring.repository.MemoryMemberRepository;
import com.cucumko.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
