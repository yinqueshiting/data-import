package com.example.dataimport.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.dataimport.util.validated.InsertValidated;
import com.example.dataimport.util.validated.SelectValidated;
import com.example.dataimport.util.validated.UpdateValidated;
import lombok.Data;
import org.springframework.context.annotation.Primary;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * (XiaoeVideoType)xiaoe_video_type: table
 *
 * @author makejava
 * @since 2020-05-27 09:38:08
 */
@TableName("xiaoe_video_type")
@Data
@Entity
public class VideoType implements Serializable {
    private static final long serialVersionUID = 126792805934910614L;
    /**
    * 自增主键
    */
    @NotNull(groups = {UpdateValidated.class},message = "查询时主键id不可为空值")
    @TableId(type = IdType.AUTO,value = "id")
    private Integer id;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 创建人的手机号
    */
    @NotBlank(groups = {InsertValidated.class},message = "创建人不可为空值")
    private String createUser;
    /**
    * 类型名称
    */
    @NotBlank(groups = {InsertValidated.class},message = "类型名称不可为空值")
    private String typeName;
    /**
    * 状态0正常9删除
    */
    private String status;







}