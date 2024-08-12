package toy.board.domain.category.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record CreateCategoryRequest(Integer parentId,
                                    @NotEmpty(message = "카테고리의 이름은 필수입니다.") String name
) {


}
