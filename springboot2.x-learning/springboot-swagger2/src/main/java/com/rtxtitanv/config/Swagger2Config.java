package com.rtxtitanv.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.config.Swagger2Config
 * @description Swagger2配置类
 * @date 2021/6/6 15:16
 */
@Configuration
@EnableKnife4j
@EnableSwagger2WebMvc
public class Swagger2Config {

    /**
     * 配置默认分组
     *
     * @return springfox.documentation.spring.web.plugins.Docket
     */
    @Bean
    public Docket defaultApi() {
        // DocumentationType.SWAGGER_2为Swagger2的文档类型
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
            /*
             * 通过apis和paths方法设置ApiSelector的构建规则
             * ApiSelectorBuilder apis(Predicate<RequestHandler> selector)
             *     RequestHandlerSelectors.any()：构建所有API
             *     RequestHandlerSelectors.none()：所有API都不构建
             *     RequestHandlerSelectors.basePackage()：构建指定包路径下的所有API
             *     RequestHandlerSelectors.withClassAnnotation()：仅构建带有指定类注解的API
             *     RequestHandlerSelectors.withMethodAnnotation()：仅构建带有指定方法注解的API
             * ApiSelectorBuilder paths(Predicate<String> selector)
             *     PathSelectors.any()：构建所有请求路径的API
             *     PathSelectors.none()：所有请求路径的API都不构建
             *     PathSelectors.regex()：仅构建正则匹配的请求路径的API
             *     PathSelectors.ant()：仅构建与ant模式匹配的API
             */
            .apis(RequestHandlerSelectors.basePackage("com.rtxtitanv.controller.other")).paths(PathSelectors.any())
            .build();
    }

    /**
     * 配置user分组
     *
     * @return springfox.documentation.spring.web.plugins.Docket
     */
    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
            .apis(RequestHandlerSelectors.basePackage("com.rtxtitanv.controller.user"))
            // groupName方法设置分组名称，globalOperationParameters方法添加全局参数
            .paths(PathSelectors.regex("/user/.*")).build().groupName("user").globalOperationParameters(token())
            .ignoredParameterTypes(HttpServletRequest.class, HttpServletResponse.class);
    }

    /**
     * 配置order分组
     *
     * @return springfox.documentation.spring.web.plugins.Docket
     */
    @Bean
    public Docket orderApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
            .apis(RequestHandlerSelectors.basePackage("com.rtxtitanv.controller.order"))
            .paths(PathSelectors.regex("/order/.*")).build().groupName("order").globalOperationParameters(token())
            .ignoredParameterTypes(HttpServletRequest.class, HttpServletResponse.class);
    }

    /**
     * 构建API文档基本信息
     *
     * @return springfox.documentation.service.ApiInfo
     */
    private ApiInfo apiInfo() {
        // 联系人信息：分别为作者、主页、邮箱
        Contact contact = new Contact("RtxTitanV", "https://blog.csdn.net/RtxTitanV", "RtxTitanV@xxx.com");
        // 构建API文档的基本信息：依次为API标题、API描述、API联系人信息、API版本、API许可、API许可Url
        return new ApiInfoBuilder().title("SpringBoot2.x 集成 Swagger2").description("SpringBoot2.x 集成 Swagger2 测试文档")
            .contact(contact).version("1.0.0").license("Apache 2.0")
            .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0").build();
    }

    /**
     * 配置JWT，在全局参数加入Authorization请求头
     *
     * @return List<Parameter>
     */
    private List<Parameter> token() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        ArrayList<Parameter> pars = new ArrayList<>();
        tokenPar.name("Authorization").description("token").modelRef(new ModelRef("string")).parameterType("header")
            .defaultValue("Bearer ").required(true).build();
        pars.add(tokenPar.build());
        return pars;
    }
}