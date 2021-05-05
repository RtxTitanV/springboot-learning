package com.rtxtitanv.repository

import com.rtxtitanv.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

/**
 * @name com.rtxtitanv.repository.AccountRepository
 * @description AccountRepository接口用于操作account表
 * @author rtxtitanv
 * @date 2020/1/20 22:30
 * @version 1.0.0
 */
interface AccountRepository : JpaRepository<Account, Long>, JpaSpecificationExecutor<Account>