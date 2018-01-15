package cn.edu.zzti.bibased.service.operation.lagou;

import cn.edu.zzti.bibased.constant.HttpHeaderConstant;
import cn.edu.zzti.bibased.dto.City;
import cn.edu.zzti.bibased.dto.Position;
import cn.edu.zzti.bibased.service.handler.LagouHandler;
import cn.edu.zzti.bibased.service.http.HttpClientService;
import cn.edu.zzti.bibased.thread.LaGouTask;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Future;

@Service
public class LagouService {
    @Resource
    private ThreadPoolTaskExecutor lagouPool;
    /**
     * 注入无阻塞的
     */
    @Resource
    private CompletionService<String> completionService;
    @Resource
    private LagouOperationService lagouOperationService;
    @Resource
    private HttpClientService httpClientService;
    public String startGetDate(String apiUrl, Map<String,Object> param,String httpType) throws Exception{
        LaGouTask laGouTask = new LaGouTask(apiUrl,param, httpType);
        for (int i = 0; i < 5; i++) {
            completionService.submit(laGouTask);
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 5; i++) {
            Future<String> take = completionService.take();
            if (take !=null) {
                buffer.append("第几次：" + i + "\n" + take.get()+"\n\n\n\n      \n");
            }
        }
        return buffer.toString();
    }

    public void initLagouInfo(){
        lagouPool.execute(new Runnable() {
            @Override
            public void run() {
                collectionJobInformation();
            }
        });
        lagouPool.execute(new Runnable() {
            @Override
            public void run() {
                collectionCityInformation();
            }
        });
    }
    /**
     * 采集拉勾网的职位信息
     *
     */
    private void collectionJobInformation(){
        String url = "https://www.lagou.com";
        String html = httpClientService.doGet(url, null, HttpHeaderConstant.lagouGetHeader);
        List<Position> jobs = LagouHandler.getJobs(html);
        lagouOperationService.batchAddJob(jobs);
    }
    /**
     * 采集拉勾网的城市信息
     *
     */
    private void collectionCityInformation(){
        String url = "https://www.lagou.com/zhaopin/Java/?labelWords=label";
        String html = httpClientService.doGet(url, null, HttpHeaderConstant.lagouGetHeader);
        List<City> jobs = LagouHandler.getCitys(html);
        lagouOperationService.batchAddCity(jobs);
    }
}
