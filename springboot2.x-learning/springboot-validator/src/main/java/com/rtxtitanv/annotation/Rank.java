package com.rtxtitanv.annotation;

import com.rtxtitanv.validator.RankValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.annotation.Rank
 * @description 自定义用户段位校验注解
 * @date 2021/8/16 15:12
 */
@Documented
@Constraint(validatedBy = RankValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Rank {

    String message() default "rank值无效";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}