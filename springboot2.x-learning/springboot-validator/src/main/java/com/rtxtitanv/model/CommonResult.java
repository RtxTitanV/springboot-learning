package com.rtxtitanv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.model.CommonResult
 * @description 通用响应类
 * @date 2021/8/15 17:35
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonResult<T> implements Serializable {

    private static final long serialVersionUID = 5231430760082814286L;
    private int code;
    private String message;
    private T data;

    public static <T> CommonResult<T> ok(String message, T data) {
        return new CommonResult<T>(HttpStatus.OK.value(), message, data);
    }

    public static <T> CommonResult<T> fail(int code, String message, T data) {
        return new CommonResult<>(code, message, data);
    }
}