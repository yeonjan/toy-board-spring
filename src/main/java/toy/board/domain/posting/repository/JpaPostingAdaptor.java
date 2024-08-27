package toy.board.domain.posting.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import toy.board.domain.posting.dto.request.PostingSearchCriteria;
import toy.board.domain.posting.repository.query.PostingQueryRepository;
import toy.board.model.entity.Member;
import toy.board.model.entity.Posting;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaPostingAdaptor implements PostingRepository {

    private final PostingJpaRepository postingJpaRepository;
    private final PostingQueryRepository postingQueryRepository;


    @Override
    public Optional<Posting> findByIdAndMember(Integer id, Member member) {
        return postingJpaRepository.findByIdAndMemberId(id, member.getId());
    }

    @Override
    public Posting save(Posting posting) {
        return postingJpaRepository.save(posting);

    }

    @Override
    public void delete(Posting posting) {
        postingJpaRepository.delete(posting);
    }

    @Override
    public Page<Posting> findAllByCriteria(Member member, Pageable pageable, PostingSearchCriteria criteria) {
        return postingQueryRepository.findAllByMemberAndCriteria(member,pageable,criteria);
    }


}
