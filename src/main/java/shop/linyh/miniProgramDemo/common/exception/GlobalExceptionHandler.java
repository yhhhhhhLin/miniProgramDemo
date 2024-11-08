package shop.linyh.miniProgramDemo.common.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.linyh.miniProgramDemo.common.BaseResponse;
import shop.linyh.miniProgramDemo.common.enums.ErrorCodeEnum;
import shop.linyh.miniProgramDemo.utils.ResultUtils;

import java.util.HashMap;

/**
 * 全局异常处理器
 *
 * @author linzz
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 自定义异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("businessException: " + e.getMessage(), e);
        HashMap<String, String> map = new HashMap<>();
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    /**
     * 系统其他异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("runtimeException", e);
        return ResultUtils.error(ErrorCodeEnum.SYSTEM_ERROR, e.getMessage());
    }
}
