package com.example.dataimport.controller;

import com.example.dataimport.entity.HhUser;
import com.example.dataimport.service.ClusterService;
import com.example.dataimport.service.MeetService;
import com.example.dataimport.util.Result;
import com.example.dataimport.util.validated.SelectValidated;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MeetController {

    private final MeetService meetService;

    private final ClusterService clusterService;

    public MeetController(MeetService meetService, ClusterService clusterService) {
        this.meetService = meetService;
        this.clusterService = clusterService;
    }

    /**
    *@date 2020/5/13 10:40
    *@auth Administrator
    *@describe 测试在一个接口中查询操作两个数据源
    *@return {@link }
    */
    @PostMapping("test")
    public void test(){
        meetService.selectMeetInfoByUserPhone();
    }


    /**
    *@date 2020/5/13 14:45
    *@auth Administrator
    *@describe 根据手机号查询用户的参会记录
    *@return {@link }
    */
    @PostMapping("selectMeetInfoByUserPhone")
    public Result selectMeetInfoByUserPhone(@RequestBody @Validated(SelectValidated.class) HhUser hhUser){
        return Result.success(meetService.selectMeetInfoByUserPhone(hhUser));
    }

    /**
    *@date 2020/5/13 14:48
    *@auth Administrator
    *@describe 通过手机号查询课程或者专栏的购买记录
    *@return {@link }
    */
    @PostMapping("selectCourseRecordsByUserPhone")
    public Result selectCourseRecordsByUserPhone(@RequestBody @Validated(SelectValidated.class) HhUser hhUser){
        return Result.success(meetService.selectCourseRecordsByUserPhone(hhUser));
    }
    /**
    *@date 2020/5/22 10:35
    *@auth Administrator
    *@describe 通过excel批量新增用户
    *@return {@link }
    */
    @PostMapping("saveUserListFromExcel")
    public Result saveUserListFromExcel(@RequestBody Map<String,String> paramMap){
        return Result.success(clusterService.saveUserListFromExcel(paramMap));
    }
}
