package com.rtxtitanv.config

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.annotation.Resource
import javax.persistence.EntityManager
import javax.sql.DataSource

/**
 * @name com.rtxtitanv.config.SecondaryConfig
 * @description 第二数据源配置类
 * @author rtxtitanv
 * @date 2020/2/5 17:34
 * @version v1.0.0
 */
@Configuration
@EnableTransactionManagement
//entityManagerFactoryRef:指定实体管理器工厂,transactionManagerRef:指定事务管理器
//basePackages:指定该数据源的repository所在包路径
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactorySecondary",
        transactionManagerRef = "transactionManagerSecondary",
        basePackages = ["com.rtxtitanv.repository.secondary"])
class SecondaryConfig(@Resource(name = "secondaryDataSource") private val secondaryDataSource: DataSource,
                      @Resource(name = "vendorProperties") private val vendorProperties: Map<String, Any>) {

    /**
     * 配置第二数据源实体管理工厂的bean
     * @param builder EntityManagerFactoryBuilder
     * @return LocalContainerEntityManagerFactoryBean
     */
    @Bean(name = ["entityManagerFactorySecondary"])
    fun entityManagerFactorySecondary(builder: EntityManagerFactoryBuilder): LocalContainerEntityManagerFactoryBean {
        return builder.dataSource(secondaryDataSource)
                //指定组合jpaProperties和hibernateProperties配置的map对象
                .properties(vendorProperties)
                //指定该数据源的实体类所在包路径
                .packages("com.rtxtitanv.model.secondary")
                .persistenceUnit("secondaryPersistenceUnit")
                .build()
    }

    /**
     * 配置第二数据源实体管理器
     * @param builder EntityManagerFactoryBuilder
     * @return EntityManager
     */
    @Bean(name = ["entityManagerSecondary"])
    fun entityManagerSecondary(builder: EntityManagerFactoryBuilder): EntityManager {
        return entityManagerFactorySecondary(builder).`object`!!.createEntityManager()
    }

    /**
     * 配置第二数据源事务管理器
     * @param builder EntityManagerFactoryBuilder
     * @return PlatformTransactionManager
     */
    @Bean(name = ["transactionManagerSecondary"])
    fun transactionManagerSecondary(builder: EntityManagerFactoryBuilder): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactorySecondary(builder).`object`!!)
    }
}