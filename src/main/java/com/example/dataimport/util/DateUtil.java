package com.example.dataimport.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    /**
    *@date 2020/5/28 17:19
    *@auth Administrator
    *@describe 获取当前时间
    *@return {@link }
    */
    public static String getCurrentTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTimeFormatter.format(localDateTime);
    }
}
