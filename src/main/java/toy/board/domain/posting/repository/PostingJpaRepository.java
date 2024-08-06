package toy.board.domain.posting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.board.model.entity.Posting;

public interface PostingJpaRepository extends JpaRepository<Posting, Integer> {


}
