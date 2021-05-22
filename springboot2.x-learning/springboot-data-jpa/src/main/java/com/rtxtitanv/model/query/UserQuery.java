package com.rtxtitanv.model.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.model.query.UserQuery
 * @description 封装用户查询条件类
 * @date 2020/1/3 15:23
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserQuery {

    private Long idQuery;
    private String userNameQuery;
    private String nickNameQuery;
    private String passWordQuery;
    private String emailQuery;
    private String telQuery;
    private Integer minAgeQuery;
    private Integer maxAgeQuery;
    private List<Long> idsQuery;
    private List<Integer> agesQuery;

}