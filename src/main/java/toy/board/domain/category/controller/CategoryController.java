package toy.board.domain.category.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toy.board.domain.category.dto.request.ChangeSequenceRequest;
import toy.board.domain.category.dto.request.CreateCategoryRequest;
import toy.board.domain.category.dto.response.CreateCategoryResponse;
import toy.board.domain.category.service.CategoryServiceImpl;
import toy.board.model.entity.Category;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    @PostMapping
    public ResponseEntity<CreateCategoryResponse> CreateCategory(@Valid @RequestBody CreateCategoryRequest request) {
        Category category = categoryService.createCategory(request);
        CreateCategoryResponse response = CreateCategoryResponse.of(category);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/sequence")
    public ResponseEntity<Void> updateCategorySequence(@Valid @RequestBody ChangeSequenceRequest request) {
        categoryService.changeSequence(request);
        return ResponseEntity.ok().build();
    }
}
