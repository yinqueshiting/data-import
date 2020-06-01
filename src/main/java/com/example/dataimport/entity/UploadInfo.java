package com.example.dataimport.entity;

import java.util.Date;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.persistence.Entity;
/**
 * (XiaoeUploadInfo)xiaoe_upload_info: table
 *
 * @author makejava
 * @since 2020-05-28 16:33:51
 */
@TableName("xiaoe_upload_info")
@Data
@Entity
public class UploadInfo implements Serializable {
    private static final long serialVersionUID = -18928862382616872L;
    /**
    * 开始时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String startTime;
    /**
    * 结束时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String endTime;
    /**
    * 上传人
    */
    private String createUser;
    /**
    * 原始文件名称
    */
    private String originalFile;
    /**
    * 目标文件路径
    */
    private String targetFile;
    /**
    * 1上传中0成功9失败
    */
    private String status;
    
    private Integer id;

    private String fileSize;

}