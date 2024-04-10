package com.choimung.boardService.respository;

import static org.junit.jupiter.api.Assertions.*;

import com.choimung.boardService.domain.Member;
import com.choimung.boardService.domain.Posts;
import com.choimung.boardService.service.PostsService;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class PostsRepositoryTest {

    @Autowired
    PostsService postsService;

    @Autowired
    EntityManager em;

    @Test
    @Rollback(value = false)
    public void 게시글_저장() {

        //given
        Member member = new Member();
        member.setName("테스터");
        em.persist(member);

        Posts post = Posts.createPost(member, "제목1", "본문1");
        Posts post2 = Posts.createPost(member, "제목2", "본문2");

        postsService.save(post);
        postsService.save(post2);

        List<Posts> postsList = member.getPostsList();
        for (Posts posts : postsList) {
            System.out.println("posts = " + posts.getTitle());
        }

        //when

        //then
    }
}