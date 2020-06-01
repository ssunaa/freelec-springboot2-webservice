package com.may.seonhwa.service;

import com.may.seonhwa.domain.posts.PostsRepository;
import com.may.seonhwa.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//생성자로 Bean객체를 받도록 하면, @Autowired와 동일할 기능을 수행
@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}
