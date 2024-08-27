package toy.board.domain.posting.dto.request;

public record PostingSearchCriteria(
        Boolean isRead,
        Integer categoryId
) {
}
