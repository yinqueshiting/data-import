package com.example.dataimport.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.dataimport.entity.XiaoeUser;
import com.example.dataimport.mapper.cluster.ClusterMeetMapper;
import com.example.dataimport.service.ClusterService;
import com.example.dataimport.util.DataListener;
import com.example.dataimport.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ClusterServiceImpl implements ClusterService {

    private final ClusterMeetMapper clusterMeetMapper;

    public ClusterServiceImpl(ClusterMeetMapper clusterMeetMapper) {
        this.clusterMeetMapper = clusterMeetMapper;
    }

    @Override
    @DS("cluster-rls")
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public Object saveUserListFromExcel(Map<String, String> paramMap) {
        String fileName = paramMap.get("fileName");
       /* XiaoeUser xiaoeUser = new XiaoeUser();
        xiaoeUser.setUserId("uus84");xiaoeUser.setAge("15");xiaoeUser.setCreateTime("2020-05-20");xiaoeUser.setName("1");xiaoeUser.setPhone("152412");xiaoeUser.setSex("男");

        XiaoeUser xiaoeUser1 = new XiaoeUser();
        xiaoeUser1.setUserId("85214");xiaoeUser1.setAge("15");xiaoeUser1.setCreateTime("2020-05-20");xiaoeUser1.setName("20");xiaoeUser1.setPhone("152412");xiaoeUser1.setSex("男");

        List<XiaoeUser> list = new ArrayList<>();
        list.add(xiaoeUser);list.add(xiaoeUser1);

        clusterMeetMapper.saveUserListFromExcel();*/
        //String fileName = "C:\\Users\\Administrator\\Desktop\\2020-02-27--2020-05-21消费用户导出1590033539 - 副本.xls";
        if(StringUtils.isBlank(fileName)){
            return Result.fail("文件路径为空");
        }
        //此方案文件流会自动关闭
        EasyExcel.read(fileName, XiaoeUser.class,new DataListener()).sheet().doRead();
        return Result.success();
    }


    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    @DS("cluster-rls")
    public void clusterUpdateUserInfo(){
        log.info("cluster内");
        clusterMeetMapper.clusterUpdateUserInfo();
    }
}
