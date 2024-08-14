package toy.board.global.response;

import java.time.LocalDateTime;

public record CommonDeleteResponse(
        Integer id,
        LocalDateTime deletedAt
) {
}
