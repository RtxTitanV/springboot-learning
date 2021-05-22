package com.rtxtitanv.repository;

import com.rtxtitanv.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.repository.AccountRepository
 * @description AccountRepository接口用于操作account表
 * @date 2020/1/3 21:37
 */
public interface AccountRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {
}