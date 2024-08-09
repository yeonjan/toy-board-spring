package toy.board.domain.category.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toy.board.model.entity.Category;
import toy.board.model.entity.Member;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
    private final CategoryJpaRepository categoryJpaRepository;

    @Override
    public Optional<Category> findById(Integer id) {
        return categoryJpaRepository.findById(id);
    }

    @Override
    public List<Category> findAllByIdIn(List<Integer> idList) {
        return categoryJpaRepository.findAllByIdIn(idList);
    }

    @Override
    public Optional<Category> findByIdAndMember(Integer categoryId, Member member) {
        return categoryJpaRepository.findByIdAndMemberId(categoryId, member.getId());
    }

    @Override
    public Category save(Category category) {
        return categoryJpaRepository.save(category);
    }

    @Override
    public void saveAll(List<Category> categoryList) {
        categoryJpaRepository.saveAll(categoryList);
    }
}
