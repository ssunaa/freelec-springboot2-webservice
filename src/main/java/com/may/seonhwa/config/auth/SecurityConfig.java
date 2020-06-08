package com.may.seonhwa.config.auth;

import com.may.seonhwa.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //시큐리티 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //h2콘솔 화면사용을 위해 해당옵션을 disable처리
        http.csrf().disable().headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**")
                    .permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) //URL별 권한관리 설정 시작
                    .anyRequest().authenticated() //설정된 값을 제외한 나머지 URL(인증된사용자만 허가)
                .and()
                    .logout()
                        .logoutSuccessUrl("/") //로그아웃성공시
                .and()
                    .oauth2Login()
                        .userInfoEndpoint() //로그인성공후 사용자정보 설정
                            .userService(customOAuth2UserService); //로그인성공후 인터페이스 구현체 등록

        super.configure(http);
    }
}
