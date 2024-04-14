package com.choimung.boardService.service;

import com.choimung.boardService.domain.Posts;
import com.choimung.boardService.respository.PostsRepository;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostsService {

    private final PostsRepository postsRepository;


    public void save(Posts posts) {
        postsRepository.save(posts);
    }

    public void delete(Long postId) {
        postsRepository.delete(postId);
    }

    public Posts findOne(Long postId) {
        return postsRepository.findOne(postId);
    }

    public List<Posts> findAll() {
        return postsRepository.findAll();
    }
}
