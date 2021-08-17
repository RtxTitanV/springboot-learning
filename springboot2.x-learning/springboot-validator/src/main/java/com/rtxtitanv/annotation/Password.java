package com.rtxtitanv.annotation;

import com.rtxtitanv.validator.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.annotation.Password
 * @description 自定义密码校验注解
 * @date 2021/8/16 14:54
 */
@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    String message() default "无效密码";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}