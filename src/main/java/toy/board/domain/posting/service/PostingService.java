package toy.board.domain.posting.service;


import toy.board.domain.posting.dto.request.SavePostingRequest;
import toy.board.domain.posting.dto.response.PostingResponse;
import toy.board.model.entity.Member;

import java.util.List;

public interface PostingService {

    List<PostingResponse> getPostingList(Member member, Integer categoryId, boolean isRead);

    Integer savePosting(Member member, SavePostingRequest requestDto);
}
