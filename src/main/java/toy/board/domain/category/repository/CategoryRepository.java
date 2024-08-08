package toy.board.domain.category.repository;

import toy.board.model.entity.Category;
import toy.board.model.entity.Member;

import java.util.Optional;

public interface CategoryRepository {
    Optional<Category> findByIdAndMember(Integer categoryId, Member member);
    Category save(Category category);
}
