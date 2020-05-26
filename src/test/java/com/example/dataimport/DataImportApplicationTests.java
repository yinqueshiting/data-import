package com.example.dataimport;

import com.alibaba.excel.EasyExcel;
import com.example.dataimport.controller.MeetController;
import com.example.dataimport.entity.XiaoeUser;
import com.example.dataimport.service.ClusterService;
import com.example.dataimport.service.MeetService;
import com.example.dataimport.util.DataListener;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class DataImportApplicationTests {

    @Autowired
    private ClusterService clusterService;
    @Autowired
    private MeetService meetService;

    @Autowired
    private MeetController meetController;
    @Test
    public void contextLoads() {
        XiaoeUser xiaoeUser = new XiaoeUser();
        xiaoeUser.setUserId("uus84");xiaoeUser.setAge("15");xiaoeUser.setCreateTime("2020-05-20");xiaoeUser.setName("1");xiaoeUser.setPhone("152412");xiaoeUser.setSex("男");

        XiaoeUser xiaoeUser1 = new XiaoeUser();
        xiaoeUser1.setUserId("85214");xiaoeUser1.setAge("15");xiaoeUser1.setCreateTime("2020-05-20");xiaoeUser1.setName("20");xiaoeUser1.setPhone("152412");xiaoeUser1.setSex("男");

        List<XiaoeUser> list = new ArrayList<>();
        list.add(xiaoeUser);list.add(xiaoeUser1);
        //-----------------------------
        List<XiaoeUser> list1 = new ArrayList<>();
        list1.add(xiaoeUser);
        list.removeAll(list1);
        System.out.println(list);


    }

}
