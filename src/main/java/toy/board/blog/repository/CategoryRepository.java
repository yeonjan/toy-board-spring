package toy.board.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toy.board.entity.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findFirstByParentIdOrderBySequenceDesc(Integer parent);

}
