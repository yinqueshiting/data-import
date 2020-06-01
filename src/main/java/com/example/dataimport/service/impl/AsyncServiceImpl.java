package com.example.dataimport.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.dataimport.entity.UploadInfo;
import com.example.dataimport.mapper.cluster.UploadInfoMapper;
import com.example.dataimport.service.AsyncService;
import com.example.dataimport.util.DateUtil;
import com.example.dataimport.util.UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {

    @Resource
    private UploadInfoMapper uploadInfoMapper;

    @Override
    @Async
    @DS("cluster-rls")
    public void asyncUploadFile(String filePath, InputStream inputStream, String fileName, String targetFileUrl, String createUser
            ,String originalName,String fileSize) {
        //新增记录
        UploadInfo uploadInfo = new UploadInfo();
        uploadInfo.setCreateUser(createUser);
       /* uploadInfo.setOriginalFile(multipartFile.getOriginalFilename());
        log.info("文件的long类型大小：{}",multipartFile.getSize());
        String fileSize = UploadUtil.getPrintSize(multipartFile.getSize());
        uploadInfo.setFileSize(fileSize);
        log.info("文件大小：{}", fileSize);*/
        uploadInfo.setOriginalFile(originalName);
        uploadInfo.setFileSize(fileSize);
        uploadInfo.setStartTime(DateUtil.getCurrentTime());
        uploadInfoMapper.addUploadInfo(uploadInfo);
        int primaryKey = uploadInfo.getId();
        try {
            //开始上传文件
            boolean flag = UploadUtil.uploadFile(filePath,inputStream,fileName);
            if(flag){
                //获取当前时间
                String endTime = DateUtil.getCurrentTime();

                uploadInfoMapper.updateUploadInfoById(primaryKey,endTime,targetFileUrl,"0");
            }

        } catch (Exception e) {
            //将插入结果修改为失败
            uploadInfoMapper.updateUploadInfoFail(primaryKey,"9");
            e.printStackTrace();
        }
    }
}
