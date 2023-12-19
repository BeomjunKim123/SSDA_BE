package com.ssda.main.member.service;


import com.ssda.main.member.entity.Member;
import com.ssda.main.member.oauth.OAuthInfoResponse;
import com.ssda.main.member.oauth.OAuthLoginParams;
import com.ssda.main.member.oauth.RequestOAuthInfoService;
import com.ssda.main.member.oauth.token.AuthTokens;
import com.ssda.main.member.oauth.token.AuthTokensGenerator;
import com.ssda.main.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
        private final MemberRepository memberRepository;
        private final AuthTokensGenerator authTokensGenerator;
        private final RequestOAuthInfoService requestOAuthInfoService;

        public AuthTokens login(OAuthLoginParams params) {
            OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
            Long memberId = findOrCreateMember(oAuthInfoResponse);
            return authTokensGenerator.generate(memberId);
        }
        private Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
                return memberRepository.findByEmail(oAuthInfoResponse.getEmail())
                        .map(Member::getId)
                        .orElseGet(() -> newMember(oAuthInfoResponse));
        }
        private Long newMember(OAuthInfoResponse oAuthInfoResponse) {
                Member member = Member.builder()
                        .email(oAuthInfoResponse.getEmail())
                        .nickname(oAuthInfoResponse.getNickname())
                        .gender(oAuthInfoResponse.getGender())
                        .age_range(oAuthInfoResponse.getAge_range())
                        .profile_image_url(oAuthInfoResponse.getProfile_image_url())
                        .birthday(oAuthInfoResponse.getBirthday())
                        .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                        .build();
                return memberRepository.save(member).getId();
        }
}
