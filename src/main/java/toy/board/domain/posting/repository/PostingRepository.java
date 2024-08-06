package toy.board.domain.posting.repository;

import toy.board.model.entity.Posting;

import java.util.Optional;

public interface PostingRepository {

    Optional<Posting> findById(Integer id);

    Posting save(Posting posting);


}
