package toy.board.domain.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.board.model.entity.Category;

import java.util.List;
import java.util.Optional;


public interface CategoryJpaRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByIdAndMemberId(Integer id, Integer memberId);

    List<Category> findAllByIdIn(List<Integer> ids);

    void saveAll(List<Category> categories);
}
