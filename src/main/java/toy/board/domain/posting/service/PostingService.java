package toy.board.domain.posting.service;


import org.springframework.data.domain.Page;
import toy.board.domain.posting.dto.request.PatchPostingRequest;
import toy.board.domain.posting.dto.request.SavePostingRequest;
import toy.board.domain.posting.dto.response.PostingResponse;
import toy.board.model.entity.Member;

public interface PostingService {

    Page<PostingResponse> getPostingList(Member member, int page, Boolean isRead, Integer categoryId);

    Integer savePosting(Member member, SavePostingRequest requestDto);

    Integer patchPosting(Member member, PatchPostingRequest requestDto, Integer postingId);

    Integer toggleReadStatus(Member member, Integer postingId);

    Integer deletePosting(Member member, Integer postingId);
}
