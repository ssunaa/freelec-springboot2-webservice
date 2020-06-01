package com.may.seonhwa.web;

import com.may.seonhwa.service.PostsService;
import com.may.seonhwa.web.dto.PostsResponseDto;
import com.may.seonhwa.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService; //final 안해주면 오류나는데 원인 모름 ㅠ

    /**
     * 등록or수정
     * @param requestDto
     * @return
     */
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    /**
     * ID찾기
     * @param id
     * @return
     */
    @GetMapping("/api/v1/posts")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

}
