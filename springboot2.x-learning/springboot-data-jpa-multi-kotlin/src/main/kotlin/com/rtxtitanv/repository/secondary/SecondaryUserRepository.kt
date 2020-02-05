package com.rtxtitanv.repository.secondary

import com.rtxtitanv.model.secondary.SecondaryUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

/**
 * @name com.rtxtitanv.repository.secondary.SecondaryUserRepository
 * @description SecondaryUserRepository用于操作从库用户表
 * @author rtxtitanv
 * @date 2020/2/5 19:02
 * @version v1.0.0
 */
interface SecondaryUserRepository : JpaRepository<SecondaryUser, Long>, JpaSpecificationExecutor<SecondaryUser>