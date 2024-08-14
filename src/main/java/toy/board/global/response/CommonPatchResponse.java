package toy.board.global.response;

import java.time.LocalDateTime;

public record CommonPatchResponse(
        Integer id,
        LocalDateTime updatedAt
) {
}
