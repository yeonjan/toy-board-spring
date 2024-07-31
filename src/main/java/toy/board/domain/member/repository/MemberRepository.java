package toy.board.domain.member.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toy.board.model.entity.Member;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final MemberJpaRepository memberJpaRepository;

    public Member save(Member member) {
        return memberJpaRepository.save(member);
    }

    public Optional<Member> findById(Integer id) {
        return memberJpaRepository.findById(Long.valueOf(id));
    }

    public Optional<Member> findByEmail(String email) {
        return memberJpaRepository.findByEmail(email);
    }

}
