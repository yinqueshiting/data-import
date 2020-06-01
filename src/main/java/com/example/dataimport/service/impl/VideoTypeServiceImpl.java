package com.example.dataimport.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dataimport.entity.UploadInfo;
import com.example.dataimport.entity.VideoType;
import com.example.dataimport.mapper.master.VideoTypeMapper;
import com.example.dataimport.service.VideoTypeService;
import com.example.dataimport.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VideoTypeServiceImpl implements VideoTypeService {

    @Resource
    private VideoTypeMapper videoTypeMapper;

    @Override
    @DS("cluster-rls")
    @Transactional(propagation = Propagation.REQUIRED)
    public Result addVideoType(VideoType videoType) {

        videoTypeMapper.addVideoType(videoType);
        return Result.success(videoType.getId());
    }

    @Override
    @DS("cluster-rls")
    @Transactional(propagation = Propagation.REQUIRED)
    public Result updateVideoType(VideoType videoType) {
        VideoType conditions = new VideoType();
        conditions.setId(videoType.getId());
        QueryWrapper<VideoType> queryWrapper = new QueryWrapper<>(conditions);
        //修改的列名为videoType中不为null的属性；条件由queryWrapper决定
        videoTypeMapper.update(videoType,queryWrapper);
        return Result.success();
    }

    @Override
    @DS("cluster-rls")
    public Result selectVideoTypes() {
        List<VideoType> typeList = videoTypeMapper.selectVideoTypes();
        return Result.success(typeList);
    }

    @Override
    @DS("cluster-rls")
    public Result selectUploadLists() {
        List<UploadInfo> infoLists =  videoTypeMapper.selectUploadLists();
        return Result.success(infoLists);
    }
}
