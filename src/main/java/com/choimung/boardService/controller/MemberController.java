package com.choimung.boardService.controller;

import com.choimung.boardService.controller.dto.MemberJoinForm;
import com.choimung.boardService.controller.dto.MemberUpdateDto;
import com.choimung.boardService.domain.Member;
import com.choimung.boardService.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

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

    @PostMapping("/logout")
    public String logOut(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String createEditForm(@SessionAttribute("loginMember") Member member, Model model) {
        Member findMember = memberService.findOne(member.getId());
        model.addAttribute("member", findMember);
        return "login/memberEdit";
    }

    @PostMapping("/edit")
    public String updateMember(@SessionAttribute("loginMember") Member member, Model model, @ModelAttribute
                               MemberUpdateDto memberUpdateDto) {
        Member findMember = memberService.findOne(member.getId());
        memberService.update(findMember.getId(), memberUpdateDto);
        return "redirect:/posts";
    }


}
