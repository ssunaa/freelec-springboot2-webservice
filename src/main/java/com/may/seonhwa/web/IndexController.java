package com.may.seonhwa.web;

import com.may.seonhwa.config.auth.dto.SessionUser;
import com.may.seonhwa.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService; //final 안해주면 오류나는데 원인 모름 ㅠ
    private final HttpSession httpSession;


    /**
     * 메인페이지 호출 및 조회
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user!=null) {
            model.addAttribute("userName", user.getName());
        }
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
