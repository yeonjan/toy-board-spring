package toy.board.domain.category.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.board.domain.category.repository.CategoryRepository;
import toy.board.global.exception.custom.EntityNotFoundException;
import toy.board.model.entity.Category;
import toy.board.model.entity.Member;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category getCategory(Member member, Integer id) {
        return categoryRepository.findByIdAndMember(id, member).orElseThrow(() -> new EntityNotFoundException("Category", id));
    }

    private static Map<Integer, Integer> getIdSequenceMap(List<IdSequenceDto> idSequenceDtoList) {
        return idSequenceDtoList.stream().collect(Collectors.toMap(IdSequenceDto::id, IdSequenceDto::sequence));
    }

    private List<Category> getMatchedCateogryList(List<IdSequenceDto> idSequenceDtoList) {
        List<Integer> idList = idSequenceDtoList.stream().map(IdSequenceDto::id).toList();
        return categoryRepository.findAllById(idList);
    }

}
