package com.example.dataimport.service;

import com.example.dataimport.entity.HhUser;
import com.example.dataimport.entity.KsMeet;

import java.util.List;

public interface MeetService {
    void selectMeetInfoByUserPhone();

    List<KsMeet> selectMeetInfoByUserPhone(HhUser hhUser);

    Object selectCourseRecordsByUserPhone(HhUser hhUser);


}
