package toy.board.domain.member.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.board.domain.member.repository.MemberRepository;
import toy.board.model.entity.Member;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member saveOrUpdate(Member member) {

        return memberRepository.findByEmail(member.getEmail())
                .map(existingMember -> updateExistingMember(existingMember, member))
                .orElseGet(() -> createNewMember(member));
    }

    private Member updateExistingMember(Member existingMember, Member newMember) {
        existingMember.updateUserInfo(newMember);
        return memberRepository.save(existingMember);
    }

    private Member createNewMember(Member member) {
        return memberRepository.save(member);
    }

    public Optional<Member> findById(Integer id) {
        return memberRepository.findById(id);
    }

    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
}
