package com.rtxtitanv.configuration

import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import javax.sql.DataSource

/**
 * @name com.rtxtitanv.config.DataSourceConfig
 * @description 多数据源配置类
 * @author rtxtitanv
 * @date 2020/2/5 16:27
 * @version 1.0.0
 */
@Configuration
class DataSourceConfig(private val jpaProperties: JpaProperties,
                       private val hibernateProperties: HibernateProperties) {

    /**
     * 配置第一数据源
     * @return 数据源
     */
    @Bean(name = ["primaryDataSource"])
    @Primary //标识为主数据源
    //prefix：指定yml配置文件中配置项的前缀
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    fun primaryDataSource(): DataSource {
        //这种方式默认只满足spring的配置方式，如果使用其他数据库连接池，需独立获取配置
        return DataSourceBuilder.create().build()
    }

    /**
     * 配置第二数据源
     * @return 数据源
     */
    @Bean(name = ["secondaryDataSource"])
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    fun secondaryDataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    /**
     * 配置 组合jpaProperties和hibernateProperties配置的map对象
     * @return 组合jpaProperties和hibernateProperties配置的map
     */
    @Bean(name = ["vendorProperties"])
    fun getVendorProperties(): Map<String, Any> {
        return hibernateProperties.determineHibernateProperties(jpaProperties.properties, HibernateSettings())
    }
}