package com.haoqi.hros.service.utils;

import com.haoqi.hros.model.OpLog;

import java.util.Date;

public class LogUtils {

    public static OpLog addLog(Byte type, String msg){
        return new OpLog(type,new Date(),msg,Hruitls.getCurrent().getName() );
    }
}
