package toy.board.domain.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toy.board.model.entity.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
