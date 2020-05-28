package com.may.seonhwa.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor //final필드 생성자 자동 생성
public class HelloResponseDto {

    private final String name;
    private final int amount;


}
