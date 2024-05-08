package toy.board.blog.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.board.blog.dto.request.CreateCategoryRequest;
import toy.board.blog.dto.response.CreateCategoryResponse;
import toy.board.blog.service.CategoryService;
import toy.board.entity.Category;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CreateCategoryResponse> CreateCategory(@Valid @RequestBody CreateCategoryRequest request) {
        Category category = categoryService.createCategory(request);
        CreateCategoryResponse response = CreateCategoryResponse.of(category);
        return ResponseEntity.ok(response);
    }

}
