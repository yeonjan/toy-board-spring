package toy.board.domain.posting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.board.model.entity.Posting;

import java.util.Optional;

public interface PostingJpaRepository extends JpaRepository<Posting, Integer> {

    Optional<Posting> findByIdAndMemberId(Integer postingId, Integer memberId);


}
