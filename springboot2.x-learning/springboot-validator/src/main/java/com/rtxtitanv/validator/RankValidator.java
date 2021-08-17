package com.rtxtitanv.validator;

import com.rtxtitanv.annotation.Rank;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.validator.RankValidator
 * @description RankValidator
 * @date 2021/8/16 15:18
 */
public class RankValidator implements ConstraintValidator<Rank, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        HashSet<String> ranks = new HashSet<>();
        ranks.add("无段位");
        ranks.add("青铜");
        ranks.add("白银");
        ranks.add("黄金");
        ranks.add("铂金");
        ranks.add("钻石");
        // 段位必须为无段位、青铜、白银、黄金、铂金、钻石之一
        return ranks.contains(value);
    }
}