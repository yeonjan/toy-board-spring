package toy.board.domain.category.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.board.domain.category.dto.request.ChangeSequenceRequest;
import toy.board.domain.category.dto.request.CreateCategoryRequest;
import toy.board.domain.category.dto.request.IdSequenceDto;
import toy.board.domain.category.repository.CategoryRepository;
import toy.board.model.entity.Category;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category getCategory(Integer id) {
        return categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Category createCategory(CreateCategoryRequest request) {
        Category parentCategory = getParentCategory(request);
        Category category = request.toCategory(parentCategory);
        category.setSequenceBy(getLastSequence(parentCategory));
        return categoryRepository.save(category);


    }

    private Category getParentCategory(CreateCategoryRequest request) {
        if (request.parentId() == null) {
            return null;
        }
        return categoryRepository.findById(request.parentId()).orElseThrow(EntityNotFoundException::new);
    }

    private Integer getLastSequence(Category parent) {
        if (parent == null) {
            return 0;
        }
        List<Category> child = parent.getChild();
        return child.stream()
                .map(Category::getSequence)
                .max(Integer::compareTo)
                .orElse(0);

    }


    public void changeSequence(ChangeSequenceRequest request) {
        List<IdSequenceDto> idSequenceDtoList = request.data();

        Map<Integer, Integer> idSequenceMap = getIdSequenceMap(idSequenceDtoList);
        List<Category> categoryList = getMatchedCateogryList(idSequenceDtoList);

        categoryList.forEach(category -> {
            Integer sequence = idSequenceMap.get(category.getId());
            category.setSequence(sequence);
        });

        categoryRepository.saveAll(categoryList); //Todo: 속도 문제가 발생할 수 있을까?
    }

    private static Map<Integer, Integer> getIdSequenceMap(List<IdSequenceDto> idSequenceDtoList) {
        return idSequenceDtoList.stream().collect(Collectors.toMap(IdSequenceDto::id, IdSequenceDto::sequence));
    }

    private List<Category> getMatchedCateogryList(List<IdSequenceDto> idSequenceDtoList) {
        List<Integer> idList = idSequenceDtoList.stream().map(IdSequenceDto::id).toList();
        return categoryRepository.findAllById(idList);
    }

}
