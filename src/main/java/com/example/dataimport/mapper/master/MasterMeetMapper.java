package com.example.dataimport.mapper.master;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.dataimport.entity.HhUser;
import com.example.dataimport.entity.KsMeet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@DS(value = "master")
@Mapper
public interface MasterMeetMapper {
    @Select("select user_id,user_name from hh_user where user_phone = #{userPhone}")
    HhUser selectUserInfo(@Param("userPhone") String userPhone);

    List<KsMeet> selectMeetInfoByUserPhone(@Param("userPhone")String userPhone);

    List<Map> selectCourseRecordsByUserPhone(@Param("columnPhone")String userPhone, @Param("coursePhone")String userPhone1);

    @Update("update hh_user set user_name = '？？' where user_id = 1006 ")
    void clusterUpdateUserInfo();
}
