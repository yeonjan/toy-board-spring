package toy.board.domain.posting.dto.response;

import java.time.LocalDateTime;

public record PostingResponse(
        Long id,
        String title,
        String description,
        String url,
        boolean isRead,
        LocalDateTime createdAt) {
}
