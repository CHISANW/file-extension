package project.exception;

import lombok.Getter;

@Getter
public class FileExtensionException extends RuntimeException{
    private final ErrorCode errorCode;

    public FileExtensionException(ErrorCode code){
        super(code.getMessage());
        this.errorCode = code;
    }

    public FileExtensionException(ErrorCode code, String message){
        super(message);
        this.errorCode = code;
    }

}
