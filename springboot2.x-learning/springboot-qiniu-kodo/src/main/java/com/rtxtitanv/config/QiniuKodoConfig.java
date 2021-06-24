package com.rtxtitanv.config;

import com.qiniu.cdn.CdnManager;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.config.QiniuKodoConfig
 * @description 七牛云对象存储配置类
 * @date 2021/6/20 12:39
 */
@ConfigurationProperties(prefix = "qiniu.kodo")
@Configuration
@Data
public class QiniuKodoConfig {

    private String accessKey;
    private String secretKey;
    private String bucket;
    private String region;
    private String domain;

    /**
     * 带有指定Region对象的配置实例
     *
     * @return com.qiniu.storage.Configuration
     */
    @Bean
    public com.qiniu.storage.Configuration config() {
        if ("huadong".equals(region)) {
            return new com.qiniu.storage.Configuration(Region.huadong());
        }
        if ("huabei".equals(region)) {
            return new com.qiniu.storage.Configuration(Region.huabei());
        }
        if ("huanan".equals(region)) {
            return new com.qiniu.storage.Configuration(Region.huanan());
        }
        if ("beimei".equals(region)) {
            return new com.qiniu.storage.Configuration(Region.beimei());
        }
        if ("xinjiapo".equals(region)) {
            return new com.qiniu.storage.Configuration(Region.xinjiapo());
        }
        return new com.qiniu.storage.Configuration();
    }

    /**
     * 七牛云上传管理器实例
     *
     * @return com.qiniu.storage.UploadManager
     */
    @Bean
    public UploadManager uploadManager() {
        return new UploadManager(config());
    }

    /**
     * 断点续传的七牛云上传管理器实例
     *
     * @return com.qiniu.storage.UploadManager
     * @throws IOException IOException
     */
    @Bean
    public UploadManager resumableUploadManager() throws IOException {
        com.qiniu.storage.Configuration config = config();
        // 指定分片上传版本
        config.resumableUploadAPIVersion = com.qiniu.storage.Configuration.ResumableUploadAPIVersion.V2;
        // 设置分片上传并发，1：采用同步上传；大于1：采用并发上传
        config.resumableUploadMaxConcurrentTaskCount = 2;
        String localTempDir = Paths.get(System.getenv("java.io.tmpdir"), bucket).toString();
        // 设置断点续传文件进度保存目录
        FileRecorder fileRecorder = new FileRecorder(localTempDir);
        return new UploadManager(config, fileRecorder);
    }

    /**
     * 认证信息实例
     *
     * @return com.qiniu.util.Auth
     */
    @Bean
    public Auth auth() {
        return Auth.create(accessKey, secretKey);
    }

    /**
     * 空间资源管理器实例
     *
     * @return com.qiniu.storage.BucketManager
     */
    @Bean
    public BucketManager bucketManager() {
        return new BucketManager(auth(), config());
    }

    /**
     * CDN管理器实例
     *
     * @return com.qiniu.cdn.CdnManager
     */
    @Bean
    public CdnManager cdnManager() {
        return new CdnManager(auth());
    }

    /**
     * 文件上传配置
     *
     * @return javax.servlet.MultipartConfigElement
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory multipartConfigFactory = new MultipartConfigFactory();
        // 设置multipart/form-data请求允许的最大数据大小，这里设置为1Gb
        multipartConfigFactory.setMaxRequestSize(DataSize.ofGigabytes(1));
        // 设置上传文件允许的最大大小，这里设置为1Gb
        multipartConfigFactory.setMaxFileSize(DataSize.ofGigabytes(1));
        return multipartConfigFactory.createMultipartConfig();
    }
}