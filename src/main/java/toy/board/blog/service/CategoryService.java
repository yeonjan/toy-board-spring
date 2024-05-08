package toy.board.blog.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.board.blog.dto.request.CreateCategoryRequest;
import toy.board.blog.repository.CategoryRepository;
import toy.board.entity.Category;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category createCategory(CreateCategoryRequest request) {
        Category parentCategory = getParentCategory(request);
        Category category = request.toCategory(parentCategory);
        category.setSequence(getLastSequence(parentCategory));
        return categoryRepository.save(category);


    }

    private Category getParentCategory(CreateCategoryRequest request) {
        if (request.parentId() == null) {
            return null;
        }
        return categoryRepository.findById(request.parentId()).orElseThrow(EntityNotFoundException::new);
    }

    private Integer getLastSequence(Category parent) {
        if (parent == null) {
            return 0;
        }
        List<Category> child = parent.getChild();
        return child.stream()
                .map(Category::getSequence)
                .max(Integer::compareTo)
                .orElse(0);

    }


}
