package project.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FileExtensionException.class)
    public ResponseEntity<ErrorResponse> handleFileExtensionException(FileExtensionException e, HttpServletRequest request) {
        log.error("FileExtensionException: {}", e.getMessage());
        ErrorResponse errorResponse = ErrorResponse.of(e.getErrorCode(), request.getRequestURI());
        return new ResponseEntity<>(errorResponse, e.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("유효성 검증에 실패했습니다.");

        ErrorResponse errorResponse = ErrorResponse.of(
                ErrorCode.BAD_REQUEST.getCode(),
                errorMessage,
                ErrorCode.BAD_REQUEST.getHttpStatus(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(errorResponse, ErrorCode.BAD_REQUEST.getHttpStatus());
    }
}
