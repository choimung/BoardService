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
import java.time.LocalDateTime;
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
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private String createBy;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

    public static Posts createPost(Member member, String title, String content) {
        Posts posts = new Posts();
        posts.setMember(member);
        posts.setTitle(title);
        posts.setContent(content);
        posts.setCreateAt(LocalDateTime.now());
        posts.setCreateBy(member.getName());
        return posts;
    }

    private void setMember(Member member) {
        this.member = member;
        member.getPostsList().add(this);
    }
}
