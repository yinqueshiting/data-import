package com.example.dataimport.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
@TableName("xiaoe_user")
public class XiaoeUser {

    @ExcelProperty("用户ID")
    private String userId;

    @ExcelProperty("昵称")
    private String name;

    @ExcelProperty("手机号")
    private String phone;

   /* @ExcelProperty("生日")
    private String birthday;*/

    @ExcelProperty("性别")
    private String sex;

    @ExcelProperty("年龄")
    private String age;

   /* @ExcelProperty("国家")
    private String country;

    @ExcelProperty("省份")
    private String province;

    @ExcelProperty("城市")
    private String city;

    @ExcelProperty("地址")
    private String address;

    @ExcelProperty("行业")
    private String industry;

    @ExcelProperty("公司")
    private String company;

    @ExcelProperty("职位")
    private String position;*/

    @ExcelProperty("注册时间")
    private String createTime;



}
