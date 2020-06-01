package com.example.dataimport.controller;

import com.example.dataimport.entity.VideoType;
import com.example.dataimport.service.AsyncService;
import com.example.dataimport.service.VideoTypeService;
import com.example.dataimport.util.Result;
import com.example.dataimport.util.UploadUtil;
import com.example.dataimport.util.validated.InsertValidated;
import com.example.dataimport.util.validated.UpdateValidated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.UUID;

/**
 * 小鹅通视频类型的增删改查
 */

@RestController
@Slf4j
public class VideoTypeController {

    @Resource
    private VideoTypeService videoTypeService;

    @Resource
    private AsyncService asyncService;
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


    @PostMapping("uploadTV/{phone}")
    public Result uploadTV(@RequestParam(value = "file") MultipartFile multipartFile, @PathVariable String phone){
        if(multipartFile == null){
            return Result.fail("文件类型不可为null");
        }
        //String realPath = request.getServletContext().getRealPath("/");
        //固定文件传到此路径
        String filePath = "/usr/tomcat/apache-tomcat-7.0.100/webapps/HappyHui/upload/xiaoe";
        //获取文件的后缀名
        int index = multipartFile.getOriginalFilename().lastIndexOf('.');
        //multipartFile.getOriginalFilename()获取文件的名称+后缀
        String suffix = multipartFile.getOriginalFilename().substring(index);
        //组成新的文件名
        String fileName = UUID.randomUUID().toString()+suffix;

        try {
            //文件url路径
            String targetFileUrl = "http://www.xingfuh.com/HappyHui/upload/xiaoe/" + fileName;
            String originalName =  multipartFile.getOriginalFilename();
            log.info("文件的long类型大小：{}",multipartFile.getSize());
            String fileSize = UploadUtil.getPrintSize(multipartFile.getSize());
            log.info("文件大小：{}",fileSize);
            //UploadUtil.uploadFile(filePath,multipartFile.getInputStream(),fileName);
            /*如果参数不是inputStream，而是multipartFile，
            * 那么在asyncUploadFile()的实现类中multipartFile.getInputStream()后会出现xxx.tmp文件不存在的情况
            * 所以originalName，fileSize，inputStream 都是在此获取作为实参。
            */
            InputStream inputStream = multipartFile.getInputStream();

            asyncService.asyncUploadFile(filePath,inputStream,fileName,targetFileUrl,phone,originalName,fileSize);
            return Result.success(targetFileUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("上传失败");
    }

    /**
    *@date 2020/5/29 9:53
    *@auth Administrator
    *@describe 查询视频上传记录列表
    *@return {@link }
    */
    @PostMapping("selectUploadLists")
    public Result selectUploadLists(){
        try{
            return videoTypeService.selectUploadLists();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }

}
