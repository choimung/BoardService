package com.choimung.boardService.respository;

import com.choimung.boardService.controller.dto.MemberUpdateDto;
import com.choimung.boardService.domain.Member;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long memberId) {
        return em.find(Member.class, memberId);
    }

    public List<Member> findByLoginId(String loginId) {
        return em.createQuery("SELECT m FROM Member m WHERE loginId =: loginId", Member.class)
                .setParameter("loginId",loginId)
                .getResultList();
    }


    public void update(Long id, MemberUpdateDto memberUpdateDto) {
        Member findMember = findOne(id);
        findMember.setPassword(memberUpdateDto.getPassword());
        findMember.setName(memberUpdateDto.getName());
    }
}
