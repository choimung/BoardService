package com.choimung.boardService.respository;

import com.choimung.boardService.domain.Posts;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class PostsRepository {

    private final EntityManager em;

    public void save(Posts posts) {
        em.persist(posts);
    }

    public void delete(Long postId) {
        Posts posts = em.find(Posts.class, postId);
        em.remove(posts);
    }

    public Posts findOne(Long postId) {
        return em.find(Posts.class, postId);
    }

    public List<Posts> findAll() {
        return em.createQuery("SELECT p FROM Posts p order by p.id desc", Posts.class).getResultList();
    }
}
