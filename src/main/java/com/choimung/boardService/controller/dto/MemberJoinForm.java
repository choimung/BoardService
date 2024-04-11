package com.choimung.boardService.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberJoinForm {

    private String loginId;

    private String password;

    private String name;
}
