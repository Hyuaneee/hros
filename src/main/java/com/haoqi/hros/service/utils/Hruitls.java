package com.haoqi.hros.service.utils;

import com.haoqi.hros.model.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

public class Hruitls {
    public static Hr getCurrent(){
        return (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }
}
