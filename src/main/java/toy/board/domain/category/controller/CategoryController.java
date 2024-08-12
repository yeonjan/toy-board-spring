package toy.board.domain.category.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import toy.board.domain.category.dto.request.ChangeSequenceRequest;
import toy.board.domain.category.dto.request.CreateCategoryRequest;
import toy.board.domain.category.dto.response.CreateCategoryResponse;
import toy.board.domain.category.service.CategoryService;
import toy.board.model.entity.Category;
import toy.board.model.entity.Member;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CreateCategoryResponse> CreateCategory(@AuthenticationPrincipal Member member, @Valid @RequestBody CreateCategoryRequest request) {
        Category category = categoryService.createCategory(member,request);
        CreateCategoryResponse response = CreateCategoryResponse.of(category);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/sequence")
    public ResponseEntity<Void> updateCategorySequence(@Valid @RequestBody ChangeSequenceRequest request) {
        categoryService.changeSequence(request);
        return ResponseEntity.ok().build();
    }
}
