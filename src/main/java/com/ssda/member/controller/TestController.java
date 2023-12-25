package com.ssda.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TestController {

    @GetMapping(value = "test")
    public String apiTest() {
        return "/test";
    }

    @GetMapping(value = "diary")
    public String diaryInsert() {
        return "/diary";
    }

    @GetMapping(value = "main")
    public String modalTest() {
        return "/main";
    }
}
