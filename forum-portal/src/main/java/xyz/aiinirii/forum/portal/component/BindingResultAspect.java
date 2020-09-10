package xyz.aiinirii.forum.portal.component;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import xyz.aiinirii.forum.common.api.CommonResult;

/**
 * 错误结果处理切面
 *
 * @author AIINIRII
 */
@Aspect
@Component
public class BindingResultAspect {

    @Pointcut("execution(public * xyz.aiinirii.forum.portal.controller.*.*(..))")
    public void bindingResult() {
    }

    @Around("bindingResult()")
    public Object doAround(ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult result = (BindingResult) arg;
                if (result.hasErrors()) {
                    FieldError fieldError = result.getFieldError();
                    if (fieldError != null) {
                        return CommonResult.validateFailed(fieldError.getDefaultMessage());
                    } else {
                        return CommonResult.validateFailed();
                    }
                }
            }
        }
        try {
            return pjp.proceed(args);
        } catch (Throwable throwable) {
            return CommonResult.failed(throwable.getMessage());
        }
    }
}
