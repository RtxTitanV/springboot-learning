package com.rtxtitanv.repository.primary

import com.rtxtitanv.model.primary.PrimaryUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

/**
 * @name com.rtxtitanv.repository.primary.PrimaryUserRepository
 * @description PrimaryUserRepository接口用于操作主库用户表
 * @author rtxtitanv
 * @date 2020/2/5 19:01
 * @version v1.0.0
 */
interface PrimaryUserRepository : JpaRepository<PrimaryUser, Long>, JpaSpecificationExecutor<PrimaryUser>