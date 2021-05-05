package com.rtxtitanv.projections

/**
 * @name com.rtxtitanv.projections.NameOnly
 * @description 只查询name的projections
 * @author rtxtitanv
 * @date 2020/1/17 17:07
 * @version 1.0.0
 */
interface NameOnly {
    fun getUserName(): String? //? 这里表示声明返回值为Nullable，即返回值允许为null
    fun getNickName(): String?
}