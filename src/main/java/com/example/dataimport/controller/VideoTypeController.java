package com.example.dataimport.controller;

import com.example.dataimport.entity.VideoType;
import com.example.dataimport.service.VideoTypeService;
import com.example.dataimport.util.Result;
import com.example.dataimport.util.validated.InsertValidated;
import com.example.dataimport.util.validated.UpdateValidated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 小鹅通视频类型的增删改查
 */

@RestController
@Slf4j
public class VideoTypeController {

    @Resource
    private VideoTypeService videoTypeService;

    /**
    *@date 2020/5/27 15:04
    *@auth Administrator
    *@describe 创建视频类型
    *@return {@link }
    */
    @PostMapping("addVideoType")
    public Result addVideoType(@RequestBody @Validated(InsertValidated.class)VideoType videoType){
        log.info("新增视频类型参数：{}",videoType);
        try{
            return videoTypeService.addVideoType(videoType);
        }catch (Exception e){
            e.printStackTrace();
        }
            return Result.fail();
    }

    /**
    *@date 2020/5/27 15:04
    *@auth Administrator
    *@describe 修改视频类型
    *@return {@link }
    */
    @PostMapping("updateVideoType")
    public Result updateVideoType(@RequestBody @Validated(UpdateValidated.class)VideoType videoType){
        log.info("修改视频类型参数：{}",videoType);
        try{
            return videoTypeService.updateVideoType(videoType);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }

    /**
    *@date 2020/5/27 15:05
    *@auth Administrator
    *@describe 查询视频类型列表
    *@return {@link }
    */
    @PostMapping("selectVideoTypes")
    public Result selectVideoTypes(){
        log.info("查询视频类型列表：{}");
        try{
            return videoTypeService.selectVideoTypes();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }


    @PostMapping("uploadTV")
    public Result uploadTV(@RequestParam(value = "file")MultipartFile multipartFile, HttpServletRequest request){
        if(multipartFile == null){
            return Result.fail("文件类型不可为null");
        }
        String realPath = request.getServletContext().getRealPath("/");
        //固定文件传到此路径
        StringBuilder filePath = new StringBuilder("http://www.xingfuh.com/HappyHui/upload/xiaoe/");
        //获取文件的后缀名
        int index = multipartFile.getOriginalFilename().lastIndexOf('.');
        //multipartFile.getOriginalFilename()获取文件的名称+后缀
        String suffix = multipartFile.getOriginalFilename().substring(index);
        filePath.append(UUID.randomUUID().toString())
                .append(suffix);
        File file = new File(filePath.toString());
        if(!file.exists()){
            file.mkdirs();
        }
        try {
            multipartFile.transferTo(file);
            return Result.success(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.fail("上传失败");
    }
}
