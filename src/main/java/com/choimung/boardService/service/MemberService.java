package com.choimung.boardService.service;

import com.choimung.boardService.domain.Member;
import com.choimung.boardService.respository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId).stream()
                .filter(m -> m.getPassword().equals(password)).findFirst()
                .orElse(null);
    }
}
