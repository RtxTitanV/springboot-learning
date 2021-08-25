package com.rtxtitanv.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.rtxtitanv.filter.XssFilter;
import com.rtxtitanv.wrapper.XssRequestWrapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.servlet.Filter;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.config.AntiSamyConfig
 * @description AntiSamy配置类
 * @date 2021/8/23 15:05
 */
@Configuration
public class AntiSamyConfig {

    /**
     * 配置XSS过滤器
     *
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>(new XssFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

    /**
     * 用于过滤Json类型数据的解析器
     *
     * @param builder Jackson2ObjectMapperBuilder
     * @return ObjectMapper
     */
    @Bean
    public ObjectMapper xssObjectMapper(Jackson2ObjectMapperBuilder builder) {
        // 创建解析器
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        // 注册解析器
        SimpleModule simpleModule = new SimpleModule("XssStringJsonSerializer");
        simpleModule.addSerializer(new XssRequestWrapper.XssStringJsonSerializer());
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }
}