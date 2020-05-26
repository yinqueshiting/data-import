package com.example.dataimport.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.dataimport.entity.XiaoeUser;
import com.example.dataimport.mapper.cluster.ClusterMeetMapper;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DataListener extends AnalysisEventListener<XiaoeUser> {

    /**
     * 因为需要存储到数据域，所以需要将mapper接口注入进来调用持久化的方法
     */
    private ClusterMeetMapper clusterMeetMapper = SpringUtil.getBean(ClusterMeetMapper.class);

    /**
     * 进行批量插入，减少每行插入带来的内存消耗，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 50;
    //不让ArrayList进行扩容操作
    List<XiaoeUser> list = new ArrayList<XiaoeUser>(60);

    //每次解析完一行后调用此方法
    @Override
    public void invoke(XiaoeUser xiaoeUser, AnalysisContext analysisContext) {
        log.info("解析到一条数据：{}",xiaoeUser);
        list.add(xiaoeUser);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    //全部解析完后调用此方法
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //如果总数量不是BATCH_COUNT的整数倍 此时还会有剩余的存在list中
       if(list.size()>0){
           saveData();
           // 存储完成清理 list
           list.clear();
       }
        log.info("解析完毕！！");
        list = null;
    }

    public void saveData(){
        clusterMeetMapper.saveUserListFromExcel(list);
    }

    /*@PostConstruct
    public void init(){
        dataListener = this;
        dataListener.clusterMeetMapper=this.clusterMeetMapper;
    }*/
}
