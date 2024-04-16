package com.choimung.boardService.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter @Setter
public class Posts {
    @Id @GeneratedValue
    @Column(name = "POSTS_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "posts")
    private List<Comments> commentsList = new ArrayList<>();

    private String title;
    private String content;
    private String createBy;
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate createAt;
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate modifiedAt;
    private String modifiedBy;

    public static Posts createPost(Member member, String title, String content) {
        Posts posts = new Posts();
        posts.setMember(member);
        posts.setTitle(title);
        posts.setContent(content);
        posts.setCreateAt(LocalDate.now());
        posts.setCreateBy(member.getName());
        return posts;
    }

    public static Posts postUpdate(Posts posts, Member member, String title, String content) {
        posts.setTitle(title);
        posts.setContent(content);
        posts.setModifiedAt(LocalDate.now());
        posts.setModifiedBy(member.getName());
        return posts;
    }

    private void setMember(Member member) {
        this.member = member;
        member.getPostsList().add(this);
    }
}
