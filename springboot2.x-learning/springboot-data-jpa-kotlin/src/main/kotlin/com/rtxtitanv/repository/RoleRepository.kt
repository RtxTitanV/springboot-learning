package com.rtxtitanv.repository

import com.rtxtitanv.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

/**
 * @name com.rtxtitanv.repository.RoleRepository
 * @description RoleRepository接口用于操作角色表
 * @author rtxtitanv
 * @date 2020/1/20 22:33
 * @version v1.0.0
 */
interface RoleRepository : JpaRepository<Role, Long>, JpaSpecificationExecutor<Role>