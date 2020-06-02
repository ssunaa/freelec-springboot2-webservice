package com.may.seonhwa.service;

import com.may.seonhwa.domain.posts.Posts;
import com.may.seonhwa.domain.posts.PostsRepository;
import com.may.seonhwa.web.dto.PostsResponseDto;
import com.may.seonhwa.web.dto.PostsSaveRequestDto;
import com.may.seonhwa.web.dto.PostsUpdateRequestDto;
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

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당게시물이 없습니다. id=" + id));

        //update시에 Repository를 호출하는부분이 없는이유는 JPA의 영속성 컨텍스트 때문
        //TODO 더티체킹
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당게시물이 없습니다. id=" + id));

        return new PostsResponseDto(posts);
    }

}
