package com.rtxtitanv.validator;

import com.rtxtitanv.annotation.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.validator.PasswordValidator
 * @description PasswordValidator
 * @date 2021/8/16 15:01
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        // 密码必须以大写英文字母开头，只包含英文字母、数字、下划线，长度在6到20之间
        return value.matches("^[A-Z][A-Za-z0-9_]{5,19}$");
    }
}