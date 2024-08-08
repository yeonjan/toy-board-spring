package toy.board.domain.category.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toy.board.model.entity.Category;
import toy.board.model.entity.Member;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
    private final CategoryJpaRepository jpaRepository;

    @Override
    public Optional<Category> findByIdAndMember(Integer categoryId, Member member) {
        return jpaRepository.findByIdAndMemberId(categoryId, member.getId());
    }

    @Override
    public Category save(Category category) {
        return jpaRepository.save(category);
    }
}
