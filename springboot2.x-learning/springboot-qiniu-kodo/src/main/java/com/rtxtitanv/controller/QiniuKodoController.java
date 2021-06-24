package com.rtxtitanv.controller;

import com.rtxtitanv.service.QiniuKodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.controller.QiniuKodoController
 * @description QiniuKodoController
 * @date 2021/6/20 12:36
 */
@RestController
public class QiniuKodoController {

    @Resource
    private QiniuKodoService qiniuKodoService;

    @PostMapping("/upload/path")
    public String uploadPath(@RequestParam(value = "localFilePath") String localFilePath,
        @RequestParam(value = "fileName") String filename, @RequestParam(value = "override") boolean override) {
        return qiniuKodoService.uploadPath(localFilePath, filename, override);
    }

    @PostMapping("/upload/bytes")
    public String uploadBytes(@RequestPart(value = "file") MultipartFile file) {
        return qiniuKodoService.uploadBytes(file);
    }

    @PostMapping("/upload/stream")
    public String uploadStream(@RequestPart(value = "file") MultipartFile file) {
        return qiniuKodoService.uploadStream(file);
    }

    @PostMapping("/upload/resumable")
    public String resumableUpload(@RequestPart(value = "file") MultipartFile file) {
        return qiniuKodoService.resumableUpload(file);
    }

    @GetMapping("/download/{fileName}")
    public void download(@PathVariable(value = "fileName") String fileName, HttpServletResponse response) {
        try {
            String fileUrl = qiniuKodoService.download(fileName);
            response.sendRedirect(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/download/private/{fileName}")
    public void privateDownload(@PathVariable(value = "fileName") String fileName, HttpServletResponse response) {
        try {
            String fileUrl = qiniuKodoService.privateDownload(fileName);
            response.sendRedirect(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/delete/{fileName}")
    public String delete(@PathVariable(value = "fileName") String fileName) {
        return qiniuKodoService.delete(fileName);
    }

    @GetMapping("/token")
    public String getUpToken() {
        return qiniuKodoService.getUpToken();
    }

    @GetMapping("/token/custom")
    public String getCustomUpToken() {
        return qiniuKodoService.getCustomUpToken();
    }

    @GetMapping("/token/callback")
    public String getUpTokenWithCallback() {
        return qiniuKodoService.getUpTokenWithCallback();
    }

    @PostMapping("/qiniu/upload/callback")
    public void qiNiuCallback(HttpServletRequest request) {
        try {
            String line = "";
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            Logger logger = LoggerFactory.getLogger(QiniuKodoController.class);
            logger.info("callbackBody:" + stringBuilder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}