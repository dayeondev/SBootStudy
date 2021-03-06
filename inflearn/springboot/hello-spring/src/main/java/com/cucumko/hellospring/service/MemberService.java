package com.cucumko.hellospring.service;

import com.cucumko.hellospring.domain.Member;
import com.cucumko.hellospring.repository.MemberRepository;
import com.cucumko.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가
     * @param member
     * @return
     */
    public Long join(Member member){

        long start = System.currentTimeMillis();

        try {
            //같은 이름이 있는 중복 회원 X
            validateDuplicateMember(member); // 중복 회원 검증

            memberRepository.save(member);
            return member.getId();
        }
        finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }


    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                } );
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMember(){
        long start = System.currentTimeMillis();

        try {
            return memberRepository.findAll();
        }
        finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMember = " + timeMs + "ms");
        }

    }

    public Optional<Member> findOne(Long memberID){
        return  memberRepository.findById(memberID);
    }
}
