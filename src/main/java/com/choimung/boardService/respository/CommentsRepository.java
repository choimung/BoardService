package com.choimung.boardService.respository;

import com.choimung.boardService.domain.Comments;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentsRepository {

    private final EntityManager em;

    public void save(Comments comments) {
        em.persist(comments);
    }


}
