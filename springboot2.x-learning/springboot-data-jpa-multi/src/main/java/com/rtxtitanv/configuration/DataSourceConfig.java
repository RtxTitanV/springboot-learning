package com.rtxtitanv.configuration;

import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.config.DataSourceConfig
 * @description 多数据源配置类
 * @date 2020/1/7 19:19
 */
@Configuration
public class DataSourceConfig {

    @Resource
    private JpaProperties jpaProperties;
    @Resource
    private HibernateProperties hibernateProperties;

    /**
     * 配置第一数据源
     * @return 数据源
     */
    @Bean(name = "primaryDataSource")
    @Primary //标识为主数据源
    //prefix：指定yml配置文件中配置项的前缀
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
        //这种方式默认只满足spring的配置方式，如果使用其他数据库连接池，需独立获取配置
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置第二数据源
     * @return 数据源
     */
    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置 组合jpaProperties和hibernateProperties配置的map对象
     * @return 组合jpaProperties和hibernateProperties配置的map
     */
    @Bean(name = "vendorProperties")
    public Map<String, Object> getVendorProperties() {
        return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
    }
}
