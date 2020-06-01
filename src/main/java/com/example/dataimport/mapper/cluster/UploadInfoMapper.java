package com.example.dataimport.mapper.cluster;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dataimport.entity.UploadInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UploadInfoMapper extends BaseMapper<UploadInfo> {
    void addUploadInfo(@Param("upload") UploadInfo uploadInfo);

    void updateUploadInfoById(@Param("id") int primaryKey, @Param("endTime") String endTime,@Param("targetFileUrl") String targetFileUrl, @Param("status") String s);

    void updateUploadInfoFail(@Param("id")int primaryKey, @Param("status")String status);
}
