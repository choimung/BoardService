package com.choimung.boardService.service;

import com.choimung.boardService.controller.dto.PostUpdateDto;
import com.choimung.boardService.domain.Member;
import com.choimung.boardService.domain.Posts;
import com.choimung.boardService.respository.PostsRepository;
import java.time.LocalDate;
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

    public void update(Long postId, Member member, PostUpdateDto postUpdateDto){

        Posts findPost = postsRepository.findOne(postId);

        if(!(findPost.getCreateBy().equals(member.getName()))) {
            throw new RuntimeException("작성자만 글을 삭제할 수 있습니다.");
        }

        Posts.postUpdate(findPost, member, postUpdateDto.getTitle(), postUpdateDto.getContent());
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
