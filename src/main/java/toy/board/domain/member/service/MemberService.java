package toy.board.domain.member.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import toy.board.domain.member.repository.MemberRepository;
import toy.board.model.entity.Member;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member saveOrUpdate(OidcUser principal) {
        OidcUserInfo userInfo = principal.getUserInfo();
        String email = userInfo.getEmail();
        String nickname = userInfo.getFullName();

        return memberRepository.findByEmail(email)
                .map(existingMember -> updateExistingMember(existingMember, nickname, email))
                .orElseGet(() -> createNewMember(nickname, email));
    }

    private Member updateExistingMember(Member existingMember, String nickname, String email) {
        existingMember.updateUserInfo(nickname, email);
        return memberRepository.save(existingMember);
    }

    private Member createNewMember(String nickname, String email) {
        Member newMember = Member.of(nickname, email);
        return memberRepository.save(newMember);
    }
}
