package com.haoqi.hros.controller.publics;

import com.haoqi.hros.model.MainNorice;
import com.haoqi.hros.model.OpLog;
import com.haoqi.hros.service.MainNoriceService;
import com.haoqi.hros.service.OplogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class HomeController {

    @Autowired
   MainNoriceService mainNoriceService;
  @Autowired
    OplogService oplogService;

    @GetMapping("/body")
    @Scheduled(cron = "0 0 3 * * ?")
    public List<MainNorice> getAllsMainNoricebody(){
        return mainNoriceService.getAllsMainNoricebody();
    }

    @GetMapping("/log")
    @Scheduled(cron = "0 0 3 * * ?")
    public List<OpLog> getAllsOplog(){
        return oplogService.getAllsOplogm();
    }



}
