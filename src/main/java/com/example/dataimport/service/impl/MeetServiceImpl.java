package com.example.dataimport.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.dataimport.entity.HhUser;
import com.example.dataimport.entity.KsMeet;
import com.example.dataimport.mapper.cluster.ClusterMeetMapper;
import com.example.dataimport.mapper.master.MasterMeetMapper;
import com.example.dataimport.service.ClusterService;
import com.example.dataimport.service.MeetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MeetServiceImpl implements MeetService {

    private final MasterMeetMapper masterMeetMapper;

    private  final ClusterMeetMapper clusterMeetMapper;

    private final ClusterService clusterService;

    public MeetServiceImpl(MasterMeetMapper masterMeetMapper, ClusterMeetMapper clusterMeetMapper, ClusterService clusterService) {
        this.masterMeetMapper = masterMeetMapper;
        this.clusterMeetMapper = clusterMeetMapper;
        this.clusterService = clusterService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    @DS("master")
    public void selectMeetInfoByUserPhone() {
       /* HhUser hhUser = masterMeetMapper.selectUserInfo("15392761520");
        log.info("主库：{}",hhUser);
        HhUser hhUser2 = clusterMeetMapper.selectUserInfoCluster("15811812185");
        log.info("从库：{}",hhUser2);*/
       log.info("master内");
       masterMeetMapper.clusterUpdateUserInfo();

        clusterService.clusterUpdateUserInfo();
        int i = 5/0;
    }

    @Override
    public List<KsMeet> selectMeetInfoByUserPhone(HhUser hhUser) {
        String userPhone = hhUser.getUserPhone();
        List<KsMeet> meetInfoList = masterMeetMapper.selectMeetInfoByUserPhone(userPhone);
        return meetInfoList;
    }

    @Override
    public Object selectCourseRecordsByUserPhone(HhUser hhUser) {
        String userPhone = hhUser.getUserPhone();
        List<Map> recordList = masterMeetMapper.selectCourseRecordsByUserPhone(userPhone,userPhone);
        return recordList;
    }


}
