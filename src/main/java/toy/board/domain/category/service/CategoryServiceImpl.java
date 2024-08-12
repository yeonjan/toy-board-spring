package toy.board.domain.category.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.board.domain.category.dto.request.ChangeSequenceRequest;
import toy.board.domain.category.dto.request.CreateCategoryRequest;
import toy.board.domain.category.dto.request.IdSequenceDto;
import toy.board.domain.category.repository.CategoryRepository;
import toy.board.global.exception.ErrorCode;
import toy.board.global.exception.custom.BusinessException;
import toy.board.global.exception.custom.EntityNotFoundException;
import toy.board.model.entity.Category;
import toy.board.model.entity.Member;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategory(Member member, Integer id) {
        return categoryRepository.findByIdAndMember(id, member).orElseThrow(() -> new EntityNotFoundException("Category", id));
    }

    @Override
    public Category createCategory(Member member, CreateCategoryRequest request) {
        Category parentCategory = getParentCategory(member, request.parentId());
        if (parentCategory.getParent() != null) throw new BusinessException(ErrorCode.CATEGORY_DEPTH_EXCEEDED);
        Category category = Category.of(member, parentCategory, request.name());
        return categoryRepository.save(category);
    }


    @Override
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

    private Category getParentCategory(Member member, Integer parentId) {
        return parentId != null ? getCategory(member, parentId) : null;
    }


    private static Map<Integer, Integer> getIdSequenceMap(List<IdSequenceDto> idSequenceDtoList) {
        return idSequenceDtoList.stream().collect(Collectors.toMap(IdSequenceDto::id, IdSequenceDto::sequence));
    }

    private List<Category> getMatchedCateogryList(List<IdSequenceDto> idSequenceDtoList) {
        List<Integer> idList = idSequenceDtoList.stream().map(IdSequenceDto::id).toList();
        return categoryRepository.findAllByIdIn(idList);
    }

}
