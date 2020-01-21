package com.rtxtitanv.model.query

/**
 * @name com.rtxtitanv.model.query.UserQuery
 * @description 封装用户查询条件类
 * @author rtxtitanv
 * @date 2020/1/17 23:55
 * @version v1.0.0
 */
data class UserQuery(var idQuery: Long? = null,
                     var userNameQuery: String? = null,
                     var nickNameQuery: String? = null,
                     var passWordQuery: String? = null,
                     var emailQuery: String? = null,
                     var telQuery: String? = null,
                     var minAgeQuery: Int? = null,
                     var maxAgeQuery: Int? = null,
                     var idsQuery: List<Long>? = null,
                     var agesQuery: List<Int>? = null)