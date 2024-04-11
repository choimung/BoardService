package com.choimung.boardService.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Comments {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POSTS_ID")
    private Posts posts;

    private String content;
    private String createBy;
    private LocalDate createAt;

    public static Comments createComment(Member member, Posts posts, String content) {
        Comments comments = new Comments();
        comments.setMember(member);
        comments.setPosts(posts);
        comments.setContent(content);
        comments.setCreateAt(LocalDate.now());
        comments.setCreateBy(member.getName());
        return comments;
    }

    private void setMember(Member member) {
        this.member = member;
        member.getCommentsList().add(this);
    }

    private void setPosts(Posts posts) {
        this.posts = posts;
        posts.getCommentsList().add(this);
    }
}
