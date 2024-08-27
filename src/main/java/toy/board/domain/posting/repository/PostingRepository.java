package toy.board.domain.posting.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import toy.board.domain.posting.dto.request.PostingSearchCriteria;
import toy.board.model.entity.Member;
import toy.board.model.entity.Posting;

import java.util.Optional;

public interface PostingRepository {

    Optional<Posting> findByIdAndMember(Integer id, Member member);

    Posting save(Posting posting);

    void delete(Posting posting);

    Page<Posting> findAllByCriteria(Member member, Pageable pageable, PostingSearchCriteria criteria);

}
