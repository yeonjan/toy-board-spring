package toy.board.domain.category.dto.request;

import jakarta.validation.constraints.NotEmpty;
import toy.board.model.entity.Category;

public record CreateCategoryRequest(Integer parentId,
                                    @NotEmpty(message = "카테고리의 이름은 필수입니다.") String name
) {
    public Category toCategory(Category parentCategory) {
        return Category.builder()
                .parent(parentCategory)
                .name(name)
                .build();
    }


}
