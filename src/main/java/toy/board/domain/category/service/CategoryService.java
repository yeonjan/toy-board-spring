package toy.board.domain.category.service;

import toy.board.domain.category.dto.request.ChangeSequenceRequest;
import toy.board.domain.category.dto.request.CreateCategoryRequest;
import toy.board.model.entity.Category;
import toy.board.model.entity.Member;

public interface CategoryService {
    Category getCategory(Member member, Integer id);

    Category createCategory(CreateCategoryRequest request);

    void changeSequence(ChangeSequenceRequest request);
}
