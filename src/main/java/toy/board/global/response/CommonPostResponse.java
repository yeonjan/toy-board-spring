package toy.board.global.response;

import java.time.LocalDateTime;

public record CommonPostResponse(
        Integer id,
        LocalDateTime createdAt
) {
}
