package xyz.aiinirii.forum.common.api;

/**
 * 封装API的错误码
 *
 * @author AIINIRII
 */
public interface IErrorCode {
    /**
     * @return API的错误码
     */
    long getCode();

    /**
     * @return API的错误信息
     */
    String getMessage();
}
