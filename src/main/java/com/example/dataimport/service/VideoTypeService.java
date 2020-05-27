package com.example.dataimport.service;

import com.example.dataimport.entity.VideoType;
import com.example.dataimport.util.Result;

public interface VideoTypeService {
    Result addVideoType(VideoType videoType);

    Result updateVideoType(VideoType videoType);

    Result selectVideoTypes();
}
