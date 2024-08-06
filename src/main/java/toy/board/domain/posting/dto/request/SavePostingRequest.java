package toy.board.domain.posting.dto.request;

public record SavePostingRequest(

        String url,
        String memo,
        Integer categoryId
) {
}
