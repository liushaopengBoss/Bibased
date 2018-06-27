package cn.edu.zzti.bibased.controller;

import cn.edu.zzti.bibased.constant.ProjectItem;
import cn.edu.zzti.bibased.queue.KafkaSender;
import cn.edu.zzti.bibased.service.job.JobService;
import cn.edu.zzti.bibased.service.operation.other.ActionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 *kafka
 *
 * Created by huaidou on  2018/6/27
 */
@Controller
@RequestMapping("/kafka")
public class KafkaController {
    private static int corePoolSize = Runtime.getRuntime().availableProcessors();
    //调整队列数 拒绝服务
    private static ThreadPoolExecutor executor  = new ThreadPoolExecutor(corePoolSize, corePoolSize+1, 10l, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(10000));
    @Autowired
    private KafkaSender kafkaSender;

    @RequestMapping(value = "/v1/start")
    @ResponseBody
    public String getProjectDetail(){
        final long killId =  100;

        for(int i=0;i<1000;i++){
            final long userId = i;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    //思考如何返回给用户信息ws
                    kafkaSender.sendChannelMess("seckill",killId+";"+userId);
                }
            };
            executor.execute(task);
        }
        return "OK";
    }



}
