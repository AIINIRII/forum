package xyz.aiinirii.forum.common.exception;

import xyz.aiinirii.forum.common.api.IErrorCode;

/**
 * 断言处理类，抛出API异常
 *
 * @author AIINIRII
 */
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}