package toy.board.domain.posting.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.board.domain.category.service.CategoryService;
import toy.board.domain.common.service.WebScrapingService;
import toy.board.domain.posting.dto.request.SavePostingRequest;
import toy.board.domain.posting.dto.response.PostingResponse;
import toy.board.domain.posting.repository.PostingRepository;
import toy.board.model.entity.Category;
import toy.board.model.entity.Member;
import toy.board.model.entity.Posting;
import toy.board.model.vo.Content;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class PostingServiceImpl implements PostingService {

    private final PostingRepository postingRepository;
    private final CategoryService categoryService;
    private final WebScrapingService webScrapingService;

    @Override
    public List<PostingResponse> getPostingList(Member member, Integer categoryId, boolean isRead) {
        return List.of();
    }

    @Override
    public Integer savePosting(Member member, SavePostingRequest requestDto) {
        Category category = categoryService.getCategory(requestDto.categoryId());
        Content content = webScrapingService.getContent(requestDto.url());
        content.setMemo(requestDto.memo());

        Posting posting = Posting.of(content, member, category);
        return postingRepository.save(posting).getId();
    }
}
