package toy.board.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.board.model.entity.Member;

import java.util.Optional;


public interface MemberJpaRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByEmail(String email);



}
