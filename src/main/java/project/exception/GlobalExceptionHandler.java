package project.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
}
