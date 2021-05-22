package com.rtxtitanv.repository;

import com.rtxtitanv.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.repository.MenuRepository
 * @description MenuRepository接口用于操作菜单表
 * @date 2020/1/3 21:37
 */
public interface MenuRepository extends JpaRepository<Menu, Long>, JpaSpecificationExecutor<Menu> {
}