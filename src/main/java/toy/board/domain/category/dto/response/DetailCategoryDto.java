package toy.board.domain.category.dto.response;

import toy.board.model.entity.Category;

public record DetailCategoryDto(Integer id, String name) {
    public DetailCategoryDto(Category category) {
        this(category.getId(), category.getName());
    }
}
