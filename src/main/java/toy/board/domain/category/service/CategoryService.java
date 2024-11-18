package toy.board.domain.category.service;

import toy.board.domain.category.dto.request.ChangeSequenceRequest;
import toy.board.domain.category.dto.request.CreateCategoryRequest;
import toy.board.domain.category.dto.response.CategoriesResponse;
import toy.board.model.entity.Category;
import toy.board.model.entity.Member;

import java.util.List;

public interface CategoryService {
    Category getCategory(Member member, Integer id);

    List<CategoriesResponse> getCategories(Member member);

    Category createCategory(Member member, CreateCategoryRequest request);

    void changeSequence(ChangeSequenceRequest request);
}
