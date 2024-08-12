package toy.board.global.response;

import toy.board.global.exception.ErrorCode;
import toy.board.global.exception.custom.BusinessException;

public record ErrorResponse(ErrorCode errorCode, String errorMessage) {
    public static ErrorResponse of(BusinessException e) {
        return new ErrorResponse(e.getErrorCode(), e.getMessage());
    }

    public static ErrorResponse of(String errorMessage) {
        return new ErrorResponse(null, errorMessage);
    }

}