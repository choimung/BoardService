package com.choimung.boardService.controller;

import com.choimung.boardService.domain.Member;
import com.choimung.boardService.domain.Posts;
import com.choimung.boardService.service.PostsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    @GetMapping
    public String posts(@SessionAttribute(value = "loginMember",required = false) Member member, Model model) {

        model.addAttribute("loginMember", member);
        List<Posts> posts = postsService.findAll();
        model.addAttribute("posts", posts);

        return "posts/posts";
    }


}
