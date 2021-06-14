package com.rtxtitanv.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.exception.InvalidParameterException
 * @description 自定义异常类，无效参数异常
 * @date 2021/6/8 18:56
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvalidParameterException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1987968660448772948L;
    private String message;
}