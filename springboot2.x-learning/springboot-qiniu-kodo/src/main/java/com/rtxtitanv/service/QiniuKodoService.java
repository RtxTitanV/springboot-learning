package com.rtxtitanv.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.service.QiniuKodoService
 * @description QiniuKodoService
 * @date 2021/6/20 12:37
 */
public interface QiniuKodoService {

    /**
     * 本地文件上传
     *
     * @param localFilePath 本地文件路径
     * @param fileName      文件名
     * @param override      是否覆盖上传凭证
     * @return 文件链接
     */
    String uploadPath(String localFilePath, String fileName, boolean override);

    /**
     * 字节数组上传
     *
     * @param file MultipartFile对象
     * @return 文件链接
     */
    String uploadBytes(MultipartFile file);

    /**
     * 数据流上传
     *
     * @param file MultipartFile对象
     * @return 文件链接
     */
    String uploadStream(MultipartFile file);

    /**
     * 断点续传
     *
     * @param file MultipartFile对象
     * @return 文件链接
     */
    String resumableUpload(MultipartFile file);

    /**
     * 公开空间文件下载
     *
     * @param fileName 文件名
     * @return 文件链接
     * @throws UnsupportedEncodingException
     */
    String download(String fileName) throws UnsupportedEncodingException;

    /**
     * 私有空间文件下载
     *
     * @param fileName 文件名
     * @return 文件链接
     * @throws UnsupportedEncodingException
     */
    String privateDownload(String fileName) throws UnsupportedEncodingException;

    /**
     * 删除空间中的文件
     *
     * @param fileName 文件名
     * @return
     */
    String delete(String fileName);

    /**
     * 获取简单上传的配置
     *
     * @return 上传凭证
     */
    String getUpToken();

    /**
     * 获取自定义上传回复的凭证
     *
     * @return 上传凭证
     */
    String getCustomUpToken();

    /**
     * 获取带回调业务服务器的凭证
     *
     * @return 上传凭证
     */
    String getUpTokenWithCallback();
}