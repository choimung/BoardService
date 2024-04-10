package com.choimung.boardService.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String loginId;

    private String password;

    private String name;

    @OneToMany(mappedBy = "member")
    private List<Posts> postsList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comments> commentsList = new ArrayList<>();

    public static Member createMember(String loginId, String password, String name) {
        Member member = new Member();
        member.setLoginId(loginId);
        member.setPassword(password);
        member.setName(name);
        return member;
    }

}
