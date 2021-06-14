package com.rtxtitanv.handler;

import com.rtxtitanv.exception.InvalidParameterException;
import com.rtxtitanv.exception.NotFoundException;
import com.rtxtitanv.model.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.handler.GlobalExceptionHandler
 * @description 全局异常处理类
 * @date 2021/6/8 18:56
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(InvalidParameterException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public CommonResult<Object> invalidParameterException(InvalidParameterException e) {
        String message = e.getMessage();
        LOGGER.error("异常信息：" + message, e);
        return CommonResult.invalidParameter(message);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public CommonResult<Object> notFoundException(NotFoundException e) {
        String message = e.getMessage();
        LOGGER.error("异常信息：" + message, e);
        return CommonResult.notFound(message);
    }
}