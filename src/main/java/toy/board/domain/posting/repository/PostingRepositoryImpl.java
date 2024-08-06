package toy.board.domain.posting.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toy.board.model.entity.Posting;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostingRepositoryImpl implements PostingRepository {

    private final PostingJpaRepository postingJpaRepository;


    @Override
    public Optional<Posting> findById(Integer id) {
        return postingJpaRepository.findById(id);
    }

    @Override
    public Posting save(Posting posting) {
        return postingJpaRepository.save(posting);
    }
}
