package com.apimonitor.system.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FormatTime {
    public static String formatTime(LocalDateTime sqltime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = formatter.format(sqltime);
        return time;
    }
    //LocalDateTime 转 Data
    public Date localDateTime2Date(LocalDateTime time){
        return  Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    //Date 转 LocalDateTime
    public LocalDateTime date2LocalDateTime(Date date){
        return  LocalDateTime.ofInstant(date.toInstant(),ZoneId.systemDefault());
    }
}
