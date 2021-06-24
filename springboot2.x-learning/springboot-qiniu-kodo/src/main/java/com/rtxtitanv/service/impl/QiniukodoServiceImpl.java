package com.rtxtitanv.service.impl;

import com.qiniu.util.StringUtils;
import com.rtxtitanv.service.QiniuKodoService;
import com.rtxtitanv.util.QiniuKodoUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.service.impl.QiniukodoServiceImpl
 * @description QiniukodoService实现类
 * @date 2021/6/20 12:37
 */
@Service
public class QiniukodoServiceImpl implements QiniuKodoService {

    @Override
    public String uploadPath(String localFilePath, String fileName, boolean override) {
        if (StringUtils.isNullOrEmpty(localFilePath)) {
            throw new RuntimeException("文件路径为空");
        }
        return QiniuKodoUtils.uploadFile(localFilePath, fileName, override);
    }

    @Override
    public String uploadBytes(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID() + "-" + originalFilename;
        if (file.isEmpty()) {
            throw new RuntimeException("文件为空");
        }
        try {
            byte[] bytes = file.getBytes();
            return QiniuKodoUtils.uploadFile(bytes, fileName);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String uploadStream(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID() + "-" + originalFilename;
        if (file.isEmpty()) {
            throw new RuntimeException("文件为空");
        }
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(file.getBytes());
            return QiniuKodoUtils.uploadFile(byteArrayInputStream, fileName);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String resumableUpload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID() + "-" + originalFilename;
        if (file.isEmpty()) {
            throw new RuntimeException("文件为空");
        }
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(file.getBytes());
            return QiniuKodoUtils.resumableUploadFile(byteArrayInputStream, fileName);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String download(String fileName) throws UnsupportedEncodingException {
        if (StringUtils.isNullOrEmpty(fileName)) {
            throw new RuntimeException("文件名为空");
        }
        return QiniuKodoUtils.getFileUrl(fileName);
    }

    @Override
    public String privateDownload(String fileName) throws UnsupportedEncodingException {
        if (StringUtils.isNullOrEmpty(fileName)) {
            throw new RuntimeException("文件名为空");
        }
        return QiniuKodoUtils.getPrivateFileUrl(fileName);
    }

    @Override
    public String delete(String fileName) {
        if (StringUtils.isNullOrEmpty(fileName)) {
            throw new RuntimeException("文件名为空");
        }
        return QiniuKodoUtils.delete(fileName);
    }

    @Override
    public String getUpToken() {
        return QiniuKodoUtils.getUpToken();
    }

    @Override
    public String getCustomUpToken() {
        return QiniuKodoUtils.getCustomUpToken();
    }

    @Override
    public String getUpTokenWithCallback() {
        return QiniuKodoUtils.getUpTokenWithCallback();
    }
}