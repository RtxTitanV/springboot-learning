package com.rtxtitanv.repository.secondary;

import com.rtxtitanv.model.secondary.SecondaryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author rtxtitanv
 * @version v1.0.0
 * @name com.rtxtitanv.repository.secondary.SecondaryUserRepository
 * @description SecondaryUserRepository用于操作从库用户表
 * @date 2020/1/7 19:37
 */
public interface SecondaryUserRepository extends JpaRepository<SecondaryUser, Long>, JpaSpecificationExecutor<SecondaryUser> {
}
