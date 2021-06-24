package com.rtxtitanv.util;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.qiniu.cdn.CdnManager;
import com.qiniu.cdn.CdnResult;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.rtxtitanv.config.QiniuKodoConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.util.QiniuKodoUtils
 * @description 七牛云对象存储工具类
 * @date 2021/6/20 12:35
 */
public class QiniuKodoUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(QiniuKodoUtils.class);
    private static final String BUCKET;
    private static final String DOMAIN;
    private static final UploadManager UPLOAD_MANAGER;
    private static final UploadManager RESUMABLE_UPLOAD_MANAGER;
    private static final BucketManager BUCKET_MANAGER;
    private static final Auth AUTH;
    private static final CdnManager CDN_MANAGER;

    static {
        QiniuKodoConfig qiniuKodoConfig = SpringUtil.getBean(QiniuKodoConfig.class);
        BUCKET = qiniuKodoConfig.getBucket();
        DOMAIN = qiniuKodoConfig.getDomain();
        UPLOAD_MANAGER = SpringUtil.getBean("uploadManager", UploadManager.class);
        RESUMABLE_UPLOAD_MANAGER = SpringUtil.getBean("resumableUploadManager", UploadManager.class);
        BUCKET_MANAGER = SpringUtil.getBean(BucketManager.class);
        AUTH = SpringUtil.getBean(Auth.class);
        CDN_MANAGER = SpringUtil.getBean(CdnManager.class);
    }

    /**
     * 简单上传的凭证
     *
     * @return 上传凭证
     */
    public static String getUpToken() {
        return AUTH.uploadToken(BUCKET);
    }

    /**
     * 覆盖上传的凭证
     *
     * @param key 要想进行覆盖的文件名称，必须与上传文件名一致
     * @return 上传凭证
     */
    public static String getUpToken(String key) {
        return AUTH.uploadToken(BUCKET, key);
    }

    /**
     * 自定义上传回复的凭证
     *
     * @return 上传凭证
     */
    public static String getCustomUpToken() {
        StringMap putPolicy = new StringMap();
        // 通过设置returnBody参数来实现返回的JSON格式的内容
        putPolicy.put("returnBody",
            "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
        long expireSeconds = 3600;
        return AUTH.uploadToken(BUCKET, null, expireSeconds, putPolicy);
    }

    /**
     * 带回调业务服务器的凭证
     *
     * @return 上传凭证
     */
    public static String getUpTokenWithCallback() {
        StringMap putPolicy = new StringMap();
        // 回调地址，该地址需要允许公网访问
        putPolicy.put("callbackUrl", "http://83527e8a63ff.ngrok.io/qiniu/upload/callback");
        putPolicy.put("callbackBody",
            "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
        putPolicy.put("callbackBodyType", "application/json");
        long expireSeconds = 3600;
        return AUTH.uploadToken(BUCKET, null, expireSeconds, putPolicy);
    }

    /**
     * 本地文件上传
     *
     * @param localFilePath 本地路径
     * @param fileName      文件名
     * @param override      是否覆盖上传凭证
     * @return 文件链接
     */
    public static String uploadFile(String localFilePath, String fileName, boolean override) {
        String upToken;
        if (override) {
            // 覆盖上传凭证
            upToken = getUpToken(fileName);
        } else {
            upToken = getUpToken();
        }
        try {
            Response response = UPLOAD_MANAGER.put(localFilePath, fileName, upToken);
            String fileUrl = fileUrl(response, fileName);
            String[] urls = {fileUrl};
            refreshFiles(urls);
            return fileUrl;
        } catch (QiniuException ex) {
            qiniuException(ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * 字节数组上传
     *
     * @param bytes    字节数组
     * @param fileName 文件名
     * @return 文件链接
     */
    public static String uploadFile(byte[] bytes, String fileName) {
        try {
            Response response = UPLOAD_MANAGER.put(bytes, fileName, getUpToken());
            return fileUrl(response, fileName);
        } catch (QiniuException ex) {
            qiniuException(ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * 数据流上传
     *
     * @param inputStream 输入流
     * @param fileName    文件名
     * @return 文件链接
     */
    public static String uploadFile(InputStream inputStream, String fileName) {
        try {
            Response response = UPLOAD_MANAGER.put(inputStream, fileName, getUpToken(), null, null);
            return fileUrl(response, fileName);
        } catch (QiniuException ex) {
            qiniuException(ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * 断点续传
     *
     * @param inputStream 输入流
     * @param fileName    文件名
     * @return 文件链接
     */
    public static String resumableUploadFile(InputStream inputStream, String fileName) {
        try {
            Response response = RESUMABLE_UPLOAD_MANAGER.put(inputStream, fileName, getUpToken(), null, null);
            return fileUrl(response, fileName);
        } catch (QiniuException ex) {
            qiniuException(ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * 手动拼接方式获取公开空间文件链接
     *
     * @param fileName 文件名
     * @return 文件链接
     * @throws UnsupportedEncodingException
     */
    public static String getFileUrl(String fileName) throws UnsupportedEncodingException {
        // 对文件名进行urlencode以兼容不同的字符
        String encoderFileName = URLEncoder.encode(fileName, "UTF-8").replace("+", "20%");
        // 拼接文件链接
        String finalUrl = String.format("%s/%s", "http://" + DOMAIN, encoderFileName);
        LOGGER.info(finalUrl);
        return finalUrl;
    }

    /**
     * 手动拼接方式获取私有空间文件链接
     *
     * @param fileName 文件名
     * @return 文件链接
     * @throws UnsupportedEncodingException
     */
    public static String getPrivateFileUrl(String fileName) throws UnsupportedEncodingException {
        // 构建对应的公开空间访问链接
        String encoderFileName = URLEncoder.encode(fileName, "UTF-8").replace("+", "20%");
        String publicUrl = String.format("%s/%s", "http://" + DOMAIN, encoderFileName);
        // 1小时，可以自定义链接过期时间
        long expireInSeconds = 3600;
        // 对该链接进行私有授权签名
        String finalUrl = AUTH.privateDownloadUrl(publicUrl, expireInSeconds);
        LOGGER.info(finalUrl);
        return finalUrl;
    }

    /**
     * 删除空间中的文件
     *
     * @param fileName 文件名
     * @return
     */
    public static String delete(String fileName) {
        try {
            Response response = BUCKET_MANAGER.delete(BUCKET, fileName);
            return response.statusCode == 200 ? "删除成功" : "删除失败";
        } catch (QiniuException ex) {
            LOGGER.error(ex.code() + "");
            LOGGER.error(ex.response.toString());
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * 文件刷新
     *
     * @param urls 待刷新的文件链接数组
     */
    public static void refreshFiles(String[] urls) {
        try {
            CdnResult.RefreshResult refreshResult = CDN_MANAGER.refreshUrls(urls);
            LOGGER.info(refreshResult.code + "");
        } catch (QiniuException e) {
            LOGGER.error(e.response.toString());
        }
    }

    /**
     * 返回文件链接
     *
     * @param response com.qiniu.http.Response
     * @param fileName 文件名
     * @return 文件链接
     * @throws QiniuException QiniuException
     */
    private static String fileUrl(Response response, String fileName) throws QiniuException {
        // 解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        LOGGER.info(putRet.key);
        LOGGER.info(putRet.hash);
        LOGGER.info("上传文件成功 {}", JSON.toJSONString(putRet));
        return "http://" + DOMAIN + "/" + fileName;
    }

    /**
     * QiniuException异常处理
     *
     * @param ex QiniuException
     */
    private static void qiniuException(QiniuException ex) {
        Response response = ex.response;
        LOGGER.error(response.toString());
        LOGGER.error("上传文件失败 {}", ex);
        try {
            LOGGER.error(response.bodyString());
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }
}