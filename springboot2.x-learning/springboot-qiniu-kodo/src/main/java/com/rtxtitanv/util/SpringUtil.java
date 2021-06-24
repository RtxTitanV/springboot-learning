package com.rtxtitanv.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.util.SpringUtil
 * @description 获取Bean的工具类
 * @date 2021/6/21 11:24
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 设置ApplicationContext
     *
     * @param applicationContext 上下文对象
     * @throws BeansException BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    /**
     * 获取ApplicationContext实例
     *
     * @return ApplicationContext实例
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过名称获取Bean
     *
     * @param name 名称
     * @return Bean
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean
     *
     * @param requiredType class对象
     * @param <T>          class所代表的类型
     * @return Bean
     */
    public static <T> T getBean(Class<T> requiredType) {
        return getApplicationContext().getBean(requiredType);
    }

    /**
     * 通过name和class获取Bean
     *
     * @param name         name
     * @param requiredType class
     * @param <T>          class所代表的类型
     * @return Bean
     */
    public static <T> T getBean(String name, Class<T> requiredType) {
        return getApplicationContext().getBean(name, requiredType);
    }
}