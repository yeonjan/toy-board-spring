package toy.board.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import toy.board.model.entity.Member;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    public Member saveLoginMember(OidcUser principal) {
        OidcUserInfo userInfo = principal.getUserInfo();
        Member member = Member.of(userInfo.getFullName(), userInfo.getEmail());

        return memberRepository
                .findByEmail(member.getEmail())
                .orElseGet(() -> memberRepository.save(member));


    }
}
