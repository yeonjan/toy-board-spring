package toy.board.domain.posting.dto.request;

public record PatchPostingRequest(
        String title,
        String memo,
        String url,
        String writer,
        Integer categoryId
) {
}
