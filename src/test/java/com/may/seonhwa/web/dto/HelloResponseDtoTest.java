package com.may.seonhwa.web.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 룸복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name); //assertThat : assertj 검증라이브러리의 검증메소드
        assertThat(dto.getAmount()).isEqualTo(amount);

        //Junit 대신 assertj를 사용한 이유 : 추가적으로 라이브러리가 불필요(ex:CoreMatchers), 자동완성이 잘 지원됨.
    }


}
