package com.may.seonhwa.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    //메인페이지 호출
    @GetMapping("/")
    public String index() {
        return "index";
    }

    //등록페이지 호출
    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
