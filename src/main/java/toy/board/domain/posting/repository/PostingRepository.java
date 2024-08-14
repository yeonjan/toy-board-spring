package toy.board.domain.posting.repository;

import toy.board.model.entity.Member;
import toy.board.model.entity.Posting;

import java.util.Optional;

public interface PostingRepository {

    Optional<Posting> findByIdAndMember(Integer id, Member member);

    Posting save(Posting posting);

    void delete(Posting posting);


}
