package com.choimung.boardService.respository;

import com.choimung.boardService.domain.Posts;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostsRepository {

    private final EntityManager em;

    public void save(Posts posts) {
        em.persist(posts);
    }

    public List<Posts> findAll() {
        return em.createQuery("SELECT p FROM Posts p ", Posts.class).getResultList();
    }
}
