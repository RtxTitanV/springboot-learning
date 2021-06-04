package com.rtxtitanv.projections;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.projections.NameOnly
 * @description 将查询结果限制为只有名称属性的projections
 * @date 2021/5/27 14:34
 */
public interface NameOnly {
    String getUsername();

    String getRealname();
}