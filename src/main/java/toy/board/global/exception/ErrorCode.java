package toy.board.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    CATEGORY_DEPTH_EXCEEDED("CATEGORY_DEPTH_EXCEEDED", "2-depth 이상의 카테고리는 생성할 수 없습니다.");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }


}