package com.example.dataimport.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface AsyncService {

    /**
     * @param filePath 在磁盘中的文件夹路径
     * @param inputStream 文件流
     * @param fileName 文件名称
     * @param targetFileUrl 文件的url完整路径
     * @param createUser 创建人手机号码
     * @param originalName 文件原名称
     * @param fileSize 文件大小
     */
    void asyncUploadFile(String filePath, InputStream inputStream, String fileName, String targetFileUrl, String createUser
        ,String originalName,String fileSize);
}
