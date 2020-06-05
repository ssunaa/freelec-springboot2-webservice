package com.may.seonhwa.web;

import com.may.seonhwa.service.PostsService;
import com.may.seonhwa.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService; //final 안해주면 오류나는데 원인 모름 ㅠ


    /**
     * 메인페이지 호출 및 조회
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    /**
     * 등록페이지 호출
     * @return
     */
    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    /**
     * 상세페이지 호출 및 조회
     * @return
     */
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(Model model, @PathVariable Long id) {
        model.addAttribute("posts", postsService.findById(id));
        return "posts-update";
    }
}
