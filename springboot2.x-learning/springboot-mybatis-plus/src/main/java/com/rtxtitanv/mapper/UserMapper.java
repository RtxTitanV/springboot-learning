package com.rtxtitanv.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rtxtitanv.model.User;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.mapper.UserMapper
 * @description UserMapper
 * @date 2021/5/16 15:43
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据ID查询
     *
     * @param id 用户ID
     * @return 查询结果
     */
    User findById(Long id);
}