package toy.board.global.auth.oauth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import toy.board.domain.member.service.MemberService;
import toy.board.global.auth.oauth.dto.CustomOidcUser;
import toy.board.global.auth.oauth.dto.OAuth2Attributes;
import toy.board.model.entity.Member;

import java.util.Collections;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomOidcUserService extends OidcUserService {

    private final MemberService memberService;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        super.setAccessibleScopes(Set.of("https://www.googleapis.com/auth/userinfo.profile", "https://www.googleapis.com/auth/userinfo.email"));
        OidcUser oidcUser = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();


        OAuth2Attributes attributes = OAuth2Attributes.of(registrationId, userNameAttributeName, oidcUser.getAttributes());

        Member member = memberService.saveOrUpdate(attributes.toEntity());
        return new CustomOidcUser(member.getId(), Collections.singleton(new SimpleGrantedAuthority(member.getRoleKey())), oidcUser.getIdToken());
    }
}
