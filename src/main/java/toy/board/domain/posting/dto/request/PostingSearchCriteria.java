package toy.board.domain.posting.dto.request;

public record PostingSearchCriteria(
        Integer page,
        Boolean isRead,
        Integer categoryId
) {
    public PostingSearchCriteria {
        if (page == null) page = 0;
    }
}
