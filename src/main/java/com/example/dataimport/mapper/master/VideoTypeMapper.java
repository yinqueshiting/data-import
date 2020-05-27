package com.example.dataimport.mapper.master;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dataimport.entity.VideoType;
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
}
