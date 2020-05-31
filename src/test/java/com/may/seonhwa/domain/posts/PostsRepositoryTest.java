package com.may.seonhwa.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    //@After -> 단위테스트가 끝날때마다 수행되는 메소드 지정
    //보통 배포전 전체 테스트중 데이터 침범을 막기위해 사용
    //여러 테스트가 동시에 수행되면 테스트용 DB인 H2에 데이터가 그대로 남아있어 실패할 수 있다
    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        //save는 id가있으면 update 없으면 insert 수행
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("seonflower91@gmail.com")
                .build()
        );

        //when
        List<Posts> list = postsRepository.findAll();

        //then
        Posts posts = list.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }

}
