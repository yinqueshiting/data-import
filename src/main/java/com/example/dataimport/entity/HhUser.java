package com.example.dataimport.entity;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.dataimport.util.validated.SelectValidated;
import lombok.Data;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

/**
 * 客户表(HhUser)实体类
 *
 * @author makejava
 * @since 2020-05-12 15:18:55
 */
@TableName("hh_user")
@Data
@Entity
public class HhUser implements Serializable {
    private static final long serialVersionUID = -82905516427541268L;
    /**
    * 用户ID
    */
    private Integer userId;
    /**
     * 客户手机号,登录账号
     */
    @NotEmpty(groups = {SelectValidated.class},message="userPhone为必传字段")
    private String userPhone;
    /**
    * 客户密码
    */
    @JSONField(serialize = false)
    private String password;
    /**
    * 注册时间
    */
    private String createTime;
    /**
    * 用户名字
    */
    private String userName;
    /**
    * 户用头像
    */
    private String headImg;
    /**
    * 别性 男1 女2
    */
    private String sex;

    @TableField(exist = false)
    private List<KsMeet> meetList;
































}