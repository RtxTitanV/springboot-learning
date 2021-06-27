package com.rtxtitanv.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.config.SmsConfig
 * @description 腾讯云短信配置类
 * @date 2021/6/25 16:21
 */
@ConfigurationProperties(prefix = "tencent.sms")
@Configuration
@Data
public class SmsConfig {
    /**
     * 腾讯云API密钥的SecretId
     */
    private String secretId;
    /**
     * 腾讯云API密钥的SecretKey
     */
    private String secretKey;
    /**
     * 短信应用的SDKAppID
     */
    private String appId;
    /**
     * 签名内容
     */
    private String sign;
    /**
     * 模板ID
     */
    private String templateId;
    /**
     * 过期时间
     */
    private String expireTime;
    /**
     * redis存储的key的前缀
     */
    private String phonePrefix;
}