package com.rtxtitanv.repository

import com.rtxtitanv.model.Menu
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

/**
 * @name com.rtxtitanv.repository.MenuRepository
 * @description MenuRepository接口用于操作菜单表
 * @author rtxtitanv
 * @date 2020/1/20 22:35
 * @version v1.0.0
 */
interface MenuRepository : JpaRepository<Menu, Long>, JpaSpecificationExecutor<Menu>