package com.may.seonhwa.config.auth.dto;

import com.may.seonhwa.domain.user.Role;
import com.may.seonhwa.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if ("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    /**
     * 구글 로그인 사용자속성
     * @param userNameAttributeName
     * @param attributes
     * @return
     */
    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String)attributes.get("name"))
                .email((String)attributes.get("email"))
                .picture((String)attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

     /**
     * 네이버 로그인 사용자속성
     * @param userNameAttributeName
     * @param attributes
     * @return
     */
     private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
         Map<String, Object> response = (Map<String, Object>) attributes.get("response");
         return OAuthAttributes.builder()
                 .name((String) response.get("name"))
                 .email((String) response.get("email"))
                 .picture((String) response.get("profile_image"))
                 .attributes(response)
                 .nameAttributeKey(userNameAttributeName)
                 .build();
     }

    /**
     * 처음 가입시 사용(기본권한 GUEST)
     * @return
     */
    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }
}
