package com.haoqi.hros.service.utils;

import com.haoqi.hros.model.EmailModel;

import java.util.concurrent.*;


public class ThreadUtils {
    //邮件发送线程池
    private static ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>());



    // 构建一个邮件发送的线程
    public static Thread getThtead(EmailModel emailModel) {
        return new Thread(() -> EmailUtils.sendGEmail(emailModel));
    }
    /**
     * @Author Liruilong
     * @Description 构造一个有缓冲功能的线程池
     * @Date 18:14 2020/2/19
     * @Param [thread]
     * @return void
     **/
    public static void getCachedThreadPool(Thread thread) {
       Executors.newCachedThreadPool().execute(thread);
    }
    /**
     * @Author Liruilong 
     * @Description 构造一个固定线程数目的线程池
     * @Date 18:21 2020/2/19
     * @Param [thread] 
     * @return void 
     **/
    public static void getFixedThreadPool(Thread thread,Integer size){
        Executors.newFixedThreadPool(size).execute(thread);
    }




    public static void getThreadPoolExecutor(EmailModel emailModel){
          ThreadUtils.threadPoolExecutor.execute(getThtead(emailModel));
    }


}
