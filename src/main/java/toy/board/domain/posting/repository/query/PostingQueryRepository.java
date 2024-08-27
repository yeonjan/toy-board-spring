package toy.board.domain.posting.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import toy.board.domain.posting.dto.request.PostingSearchCriteria;
import toy.board.model.entity.Member;
import toy.board.model.entity.Posting;

public interface PostingQueryRepository {
    Page<Posting> findAllByMemberAndCriteria(Member member, Pageable pageable, PostingSearchCriteria criteria);
}
