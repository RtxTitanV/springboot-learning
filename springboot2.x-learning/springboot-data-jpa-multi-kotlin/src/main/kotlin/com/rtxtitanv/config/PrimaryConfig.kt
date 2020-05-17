package com.rtxtitanv.config

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.annotation.Resource
import javax.persistence.EntityManager
import javax.sql.DataSource

/**
 * @name com.rtxtitanv.config.PrimaryConfig
 * @description 第一数据源配置类
 * @author rtxtitanv
 * @date 2020/2/5 17:33
 * @version v1.0.0
 */
@Configuration
@EnableTransactionManagement
//entityManagerFactoryRef：指定实体管理器工厂，transactionManagerRef：指定事务管理器
//basePackages：指定该数据源的repository所在包路径
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryPrimary",
        transactionManagerRef = "transactionManagerPrimary",
        basePackages = ["com.rtxtitanv.repository.primary"])
class PrimaryConfig(@Resource(name = "primaryDataSource") private val primaryDataSource: DataSource,
                    @Resource(name = "vendorProperties") private val vendorProperties: Map<String, Any>) {

    /**
     * 配置第一数据源实体管理工厂的bean
     * @param builder EntityManagerFactoryBuilder
     * @return LocalContainerEntityManagerFactoryBean
     */
    @Bean(name = ["entityManagerFactoryPrimary"])
    @Primary //标识为主数据源（主库对应的数据源）
    fun entityManagerFactoryPrimary(builder: EntityManagerFactoryBuilder): LocalContainerEntityManagerFactoryBean {
        return builder.dataSource(primaryDataSource)
                //指定组合jpaProperties和hibernateProperties配置的map对象
                .properties(vendorProperties)
                //指定该数据源的实体类所在包路径
                .packages("com.rtxtitanv.model.primary")
                .persistenceUnit("primaryPersistenceUnit")
                .build()
    }

    /**
     * 配置第一数据源实体管理器
     * @param builder EntityManagerFactoryBuilder
     * @return EntityManager
     */
    @Bean(name = ["entityManagerPrimary"])
    @Primary
    fun entityManagerPrimary(builder: EntityManagerFactoryBuilder): EntityManager {
        return entityManagerFactoryPrimary(builder).`object`!!.createEntityManager()
    }

    /**
     * 配置第一数据源事务管理器
     * @param builder EntityManagerFactoryBuilder
     * @return PlatformTransactionManager
     */
    @Bean(name = ["transactionManagerPrimary"])
    @Primary
    fun transactionManagerPrimary(builder: EntityManagerFactoryBuilder): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactoryPrimary(builder).`object`!!)
    }
}