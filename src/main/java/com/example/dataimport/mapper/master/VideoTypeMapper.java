package com.example.dataimport.mapper.master;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dataimport.entity.UploadInfo;
import com.example.dataimport.entity.VideoType;
import com.example.dataimport.util.Result;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface VideoTypeMapper extends BaseMapper<VideoType> {
    int addVideoType(@Param("videoType")VideoType videoType);

    @Select("select type.id,type.type_name,type.create_time,users.SCA06 create_user,type.status from xiaoe_video_type type inner join SCA1 users on type.create_user = users.SCA26")
    List<VideoType> selectVideoTypes();

    @Select("select DISTINCT info.start_time,info.end_time,info.original_file,info.target_file,info.status,info.id,info.file_size,users.SCA06 create_user from xiaoe_upload_info info inner join SCA1 users on info.create_user = users.SCA26 order By info.id desc")
    List<UploadInfo> selectUploadLists();
}
