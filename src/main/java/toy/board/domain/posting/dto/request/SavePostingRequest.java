package toy.board.domain.posting.dto.request;

import jakarta.validation.constraints.NotNull;

public record SavePostingRequest(

        @NotNull String url,
        String memo,
        Integer categoryId
) {
}
