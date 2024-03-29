package com.rtxtitanv.handler;

import com.rtxtitanv.model.CommonResult;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.handler.GlobalExceptionHandler
 * @description 全局异常处理类
 * @date 2021/8/15 16:36
 */
@RestControllerAdvice(annotations = {Controller.class, RestController.class})
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public CommonResult<Map<String, String>> validateException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>(16);
        e.getBindingResult().getAllErrors()
            .forEach(error -> errors.put(((FieldError)error).getField(), error.getDefaultMessage()));
        return CommonResult.fail(HttpStatus.BAD_REQUEST.value(), "无效的参数", errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult<Map<String, String>> handleConstraintViolationException(ConstraintViolationException e) {
        Map<String, String> errors = new HashMap<>(16);
        e.getConstraintViolations().forEach(constraintViolation -> errors
            .put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage()));
        return CommonResult.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "无效的参数", errors);
    }
}