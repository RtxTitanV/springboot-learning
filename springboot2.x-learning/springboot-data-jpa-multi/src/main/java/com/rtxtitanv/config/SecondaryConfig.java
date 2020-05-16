package com.rtxtitanv.config;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @author rtxtitanv
 * @version v1.0.0
 * @name com.rtxtitanv.config.SecondaryConfig
 * @description 第二数据源配置类
 * @date 2020/1/7 19:21
 */
@Configuration
@EnableTransactionManagement
//entityManagerFactoryRef：指定实体管理器工厂，transactionManagerRef：指定事务管理器
//basePackages：指定该数据源的repository所在包路径
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactorySecondary",
        transactionManagerRef = "transactionManagerSecondary",
        basePackages = {"com.rtxtitanv.repository.secondary"})
public class SecondaryConfig {

    @Resource(name = "secondaryDataSource")
    private DataSource secondaryDataSource;
    @Resource(name = "vendorProperties")
    private Map<String, Object> vendorProperties;

    /**
     * 配置第二数据源实体管理工厂的bean
     * @param builder EntityManagerFactoryBuilder
     * @return LocalContainerEntityManagerFactoryBean
     */
    @Bean(name = "entityManagerFactorySecondary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(secondaryDataSource)
                //指定组合jpaProperties和hibernateProperties配置的map对象
                .properties(vendorProperties)
                //指定该数据源的实体类所在包路径
                .packages("com.rtxtitanv.model.secondary")
                .persistenceUnit("secondaryPersistenceUnit")
                .build();
    }

    /**
     * 配置第二数据源实体管理器
     * @param builder EntityManagerFactoryBuilder
     * @return EntityManager
     */
    @Bean(name = "entityManagerSecondary")
    public EntityManager entityManagerSecondary(EntityManagerFactoryBuilder builder) {
        return entityManagerFactorySecondary(builder).getObject().createEntityManager();
    }

    /**
     * 配置第二数据源事务管理器
     * @param builder EntityManagerFactoryBuilder
     * @return PlatformTransactionManager
     */
    @Bean(name = "transactionManagerSecondary")
    public PlatformTransactionManager transactionManagerSecondary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactorySecondary(builder).getObject());
    }
}
