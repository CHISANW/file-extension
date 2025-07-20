package project.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    NOT_FOUND("COM001", "데이터를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    BAD_REQUEST("COM002", "잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("COM003", "서버 내부 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED("COM004", "인증되지 않은 접근입니다.", HttpStatus.UNAUTHORIZED),
    FORBIDDEN("COM005", "접근 권한이 없습니다.", HttpStatus.FORBIDDEN),
    ALREADY("FLE001","이미 등록된 확장자 입니다.",HttpStatus.BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
