package com.choimung.boardService.controller;

import com.choimung.boardService.controller.dto.MemberLoginForm;
import com.choimung.boardService.domain.Member;
import com.choimung.boardService.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("form", new MemberLoginForm());
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("form") MemberLoginForm form, BindingResult bindingResult, HttpServletRequest request) {
        Member loginMember = memberService.login(form.getLoginId(), form.getPassword());

        if(loginMember == null) {
            bindingResult.reject("loginFail", "ID OR PW ERROR");
        }

        if(bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("loginMember", loginMember);
        return "redirect:/posts";
    }

}
