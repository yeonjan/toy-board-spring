package toy.board.blog.dto.response;

import toy.board.model.entity.Category;

public record CreateCategoryResponse(Integer id, String name, Integer sequence) {

    public static CreateCategoryResponse of(Category category) {
        return new CreateCategoryResponse(category.getId(), category.getName(), category.getSequence());
    }
}
