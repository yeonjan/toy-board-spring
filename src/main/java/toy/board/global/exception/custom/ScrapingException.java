package toy.board.global.exception.custom;

public class ScrapingException extends RuntimeException {
    public ScrapingException() {
        super("URL에서 정보를 불러올 수 없습니다.");
    }
}
