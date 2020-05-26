package com.example.dataimport.mapper.cluster;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dataimport.entity.HhUser;
import com.example.dataimport.entity.XiaoeUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@DS(value = "cluster-rls")
@Mapper
public interface ClusterMeetMapper  {

    @Select("select user_id,user_name from hh_user where user_phone = #{userPhone}")
    HhUser selectUserInfoCluster(@Param("userPhone") String userPhone);

    void saveUserListFromExcel(@Param("list") List<XiaoeUser> xiaoeUserList);

    @Update("update hh_user set user_name = '懂1' where user_id = 2 ")
    void clusterUpdateUserInfo();
}
