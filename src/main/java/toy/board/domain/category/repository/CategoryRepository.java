package toy.board.domain.category.repository;

import toy.board.model.entity.Category;
import toy.board.model.entity.Member;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Optional<Category> findById(Integer id);

    List<Category> findAllByIdIn(List<Integer> idList);

    List<Category> findWithChildByMember(Member member);

    Optional<Category> findByIdAndMember(Integer categoryId, Member member);

    Category save(Category category);

    void saveAll(List<Category> categoryList);
}
