package com.choimung.boardService.controller;

import com.choimung.boardService.controller.dto.PostAddFrom;
import com.choimung.boardService.domain.Member;
import com.choimung.boardService.domain.Posts;
import com.choimung.boardService.service.MemberService;
import com.choimung.boardService.service.PostsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;
    private final MemberService memberService;

    @GetMapping
    public String posts(@SessionAttribute(value = "loginMember",required = false) Member member, Model model) {
        List<Posts> posts = postsService.findAll();
        model.addAttribute("loginMember", member);
        model.addAttribute("posts", posts);
        return "posts/posts";
    }

    @GetMapping("/add")
    public String postAddForm(Model model) {
        model.addAttribute("form", new PostAddFrom());
        return "posts/postsAddForm";
    }

    @PostMapping("/add")
    public String postAdd(@SessionAttribute(name = "loginMember",required = false) Member member,@ModelAttribute PostAddFrom form) {

        Member findMember = memberService.findOne(member.getId());
        Posts post = Posts.createPost(findMember, form.getTitle(), form.getContent());
        postsService.save(post);

        return "redirect:/posts";
    }


}
