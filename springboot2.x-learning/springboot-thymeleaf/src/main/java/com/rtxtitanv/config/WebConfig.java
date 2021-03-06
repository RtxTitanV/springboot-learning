package com.rtxtitanv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.config.WebConfig
 * @description 配置自定义国际化解析器
 * @date 2021/7/6 16:25
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

    /**
     * 自定义LocaleResolver
     */
    public static class MyLocaleResolver implements LocaleResolver {
        @Override
        public Locale resolveLocale(HttpServletRequest request) {
            // 接收语言参数
            String lang = request.getParameter("lang");
            // 使用默认语言
            Locale locale = Locale.getDefault();
            // 语言参数不为空就设置为该语言
            if (!StringUtils.isEmptyOrWhitespace(lang)) {
                String[] s = lang.split("_");
                locale = new Locale(s[0], s[1]);
            }
            return locale;
        }

        @Override
        public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {}

    }
}