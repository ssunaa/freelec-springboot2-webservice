package com.may.seonhwa.config.auth;

import com.may.seonhwa.config.auth.dto.OAuthAttributes;
import com.may.seonhwa.config.auth.dto.SessionUser;
import com.may.seonhwa.domain.user.User;
import com.may.seonhwa.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        //현재 로그인서비스 구분값
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        //현재 로그인서비스의 키값(구글의 기본코드'sub')
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        //사용자정보
        OAuthAttributes attributes = OAuthAttributes.of(registrationId
                , userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);

        //TODO user를 안쓰고 새로 만들어서 쓰는이유
        //직렬화를 위해서 DTO 분리 User클래스는 Entity자체인데,
        //Entity를 직렬화를 시켜버리면 추후 다른 연관 Entity에도 영향을 미쳐 성능이슈, 부수효과가 발생하기 때문
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(
                Collections.singleton(
                        new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }


}
