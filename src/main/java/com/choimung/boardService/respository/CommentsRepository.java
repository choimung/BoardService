package com.choimung.boardService.respository;

import com.choimung.boardService.domain.Comments;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional
public class CommentsRepository {

    private final EntityManager em;

    public void save(Comments comments) {
        em.persist(comments);
    }

    public List<Comments> findByPostId(Long postId) {
        return em.createQuery("select c from Comments c where c.posts.id =:postId", Comments.class)
                .setParameter("postId", postId)
                .getResultList();
    }


}
