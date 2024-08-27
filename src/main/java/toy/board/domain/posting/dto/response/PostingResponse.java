package toy.board.domain.posting.dto.response;

import toy.board.model.entity.Posting;

import java.time.LocalDateTime;

public record PostingResponse(
        Integer id,
        String title,
        String memo,
        String url,
        boolean isRead,
        LocalDateTime createdAt) {

    public PostingResponse(Posting posting) {
        this(posting.getId(), posting.getContent().getTitle(), posting.getContent().getMemo(), posting.getContent().getUrl(), posting.isRead(), posting.getCreatedAt());
    }
}
