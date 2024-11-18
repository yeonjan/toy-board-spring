package toy.board.domain.category.dto.response;

import toy.board.model.entity.Category;

import java.util.List;

public record CategoriesResponse(Integer id, String name, List<DetailCategoryDto> subCategories) {

    public CategoriesResponse(Category category) {
        this(
                category.getId(),
                category.getName(),
                category.getChild().stream()
                        .map(DetailCategoryDto::new)
                        .toList()
        );
    }

}
