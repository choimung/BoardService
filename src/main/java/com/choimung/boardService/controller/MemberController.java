package com.choimung.boardService.controller;

import com.choimung.boardService.domain.Member;
import com.choimung.boardService.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String memberJoinForm(Model model) {
        model.addAttribute("form", new MemberJoinForm());
        return "member/memberJoinForm";
    }

    @PostMapping("/signup")
    public String memberJoin(@ModelAttribute MemberJoinForm form) {
        Member member = Member.createMember(form.getLoginId(), form.getPassword(), form.getName());
        memberService.join(member);
        return "redirect:/";
    }


}
