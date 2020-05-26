package com.example.dataimport.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import javax.persistence.Entity;
/**
 * 线下课程会议表(KsMeet)ks_meet: table
 *
 * @author makejava
 * @since 2020-05-13 11:05:00
 */
@TableName("ks_meet")
@Data
@Entity
public class KsMeet implements Serializable {
    private static final long serialVersionUID = -48872531465529521L;
    /**
    * 会议id
    */
    private String meetId;
    /**
    * 会议展示图
    */
    private String meetPic;
    /**
    * 会议名称
    */
    private String meetName;
    /**
    * 课程id
    */
    private String courseId;

    /**
     * 参会时间
     */
    private String createTime;
    /**
    * 开始时间
    */
    private String beginTime;
    /**
    * 结束时间
    */
    private String endTime;
    /**
    * 会议地址
    */
    private String address;

    /**
    * 会议讲师名字
    */
    private String teacherName;

    /**
    * 导师id
    */
    private String teacherId;

    /**
    * H5页面报名背景图i地址
    */
    private String h5Pic;

    @TableField(exist = false)
    private HhUser hhUser;










































}