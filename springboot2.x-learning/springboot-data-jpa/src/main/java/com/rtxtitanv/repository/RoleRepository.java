package com.rtxtitanv.repository;

import com.rtxtitanv.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author rtxtitanv
 * @version v1.0.0
 * @name com.rtxtitanv.repository.RoleRepository
 * @description RoleRepository接口用于操作角色表
 * @date 2020/1/3 21:37
 */
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
}
