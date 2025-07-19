package project.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
    private final LocalDateTime timestamp;
    private final String code;
    private final String message;
    private final HttpStatus status;
    private final String path;

    public static ErrorResponse of(ErrorCode errorCode, String path) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .status(errorCode.getHttpStatus())
                .path(path)
                .build();
    }

    public static ErrorResponse of(ErrorCode errorCode, String message, String path) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .code(errorCode.getCode())
                .message(message)
                .status(errorCode.getHttpStatus())
                .path(path)
                .build();
    }

    public static ErrorResponse of(String code, String message, HttpStatus status, String path) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .code(code)
                .message(message)
                .status(status)
                .path(path)
                .build();
    }
}