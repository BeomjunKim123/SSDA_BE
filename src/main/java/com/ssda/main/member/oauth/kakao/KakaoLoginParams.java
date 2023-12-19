package com.ssda.main.member.oauth.kakao;

import com.ssda.main.member.oauth.OAuthLoginParams;
import com.ssda.main.member.oauth.OAuthProvider;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@NoArgsConstructor
public class KakaoLoginParams implements OAuthLoginParams {
    private String authorizationCode;
    @Override
    public OAuthProvider oAuthProvider() {
        return OAuthProvider.KAKAO;
    }
    @Override
    public MultiValueMap<String, String> makeBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", authorizationCode);
        return body;
    }
}
