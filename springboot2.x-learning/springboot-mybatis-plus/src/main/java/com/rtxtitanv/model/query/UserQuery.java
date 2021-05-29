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
 * @date 2021/5/20 15:25
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserQuery {
    private Long idQuery;
    private String usernameQuery;
    private String passwordQuery;
    private String realnameQuery;
    private String genderQuery;
    private Integer ageQuery;
    private Integer minAgeQuery;
    private Integer maxAgeQuery;
    private String emailQuery;
    private Integer minUserPointQuery;
    private Integer maxUserPointQuery;
    private Byte userLevelQuery;
    private List<Long> idsQuery;
    private List<Integer> agesQuery;
    private List<Integer> userPointsQuery;
    private List<Byte> userLevelsQuery;
}