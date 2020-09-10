package xyz.aiinirii.forum.common.exception;

import lombok.Getter;
import xyz.aiinirii.forum.common.api.IErrorCode;

/**
 * API异常
 *
 * @author AIINIRII
 */
@Getter
public class ApiException extends RuntimeException {
    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

}