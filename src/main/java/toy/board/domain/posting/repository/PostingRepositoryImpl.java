package toy.board.domain.posting.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toy.board.model.entity.Member;
import toy.board.model.entity.Posting;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostingRepositoryImpl implements PostingRepository {

    private final PostingJpaRepository postingJpaRepository;


    @Override
    public Optional<Posting> findByIdAndMember(Integer id, Member member) {
        return postingJpaRepository.findByIdAndMemberId(id, member.getId());
    }

    @Override
    public Posting save(Posting posting) {
        return postingJpaRepository.save(posting);
    }
}
