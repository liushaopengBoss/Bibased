package cn.edu.zzti.bibased.service.operation.boss;

import cn.edu.zzti.bibased.constant.HttpHeaderConstant;
import cn.edu.zzti.bibased.constant.WebsiteEnum;
import cn.edu.zzti.bibased.dto.City;
import cn.edu.zzti.bibased.dto.Positions;
import cn.edu.zzti.bibased.dto.boss.BossBaseVo;
import cn.edu.zzti.bibased.service.email.EmailService;
import cn.edu.zzti.bibased.service.handler.BossHandler;
import cn.edu.zzti.bibased.service.http.HttpClientService;
import cn.edu.zzti.bibased.service.operation.base.AcquisitionService;
import cn.edu.zzti.bibased.service.operation.lagou.LagouQueryService;
import cn.edu.zzti.bibased.utils.IDUtils;
import com.alibaba.fastjson.JSON;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.net.IDN;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Future;

@Service
public class BossGetService {

    @Resource
    private ThreadPoolTaskExecutor getInfoPool;
    /**
     * 注入无阻塞的
     */
    @Resource
    private CompletionService completionService;
    @Resource
    private BossQueryService bossQueryService;
    @Resource
    private HttpClientService httpClientService;
    @Resource
    private EmailService emailService;
    @Resource
    private AcquisitionService acquisitionService;

    public void getBossPositionType(){
        String apiUrl = "https://www.zhipin.com/";
        String html = httpClientService.doGet(apiUrl, null, HttpHeaderConstant.bossGetHeader);
        List<Positions> jobs = BossHandler.getJobs(html);
        acquisitionService.batchAddJob(jobs);
    }

    public void getBossPositionTypeV2(){
        List<Positions> positionType = this.getPositionType();
        acquisitionService.batchAddJob(positionType);
    }

    private List<Positions> getPositionType(){
        String apiUrl = "https://www.zhipin.com/common/data/position.json";
        String  sourceJson = httpClientService.doGet(apiUrl,null,HttpHeaderConstant.bossGetHeader);
        return BossHandler.getJobsV2(sourceJson);
    }

    public void getCity(){
        String apiUrl = "https://www.zhipin.com/common/data/city.json";
        String  sourceJson = httpClientService.doGet(apiUrl,null,HttpHeaderConstant.bossGetHeader);
        List<City> city = BossHandler.getCity(sourceJson);
        acquisitionService.batchAddCity(city);
    }

   public void getPositionDetails(){
       Future<List<City>> cityFuture = getInfoPool.submit(() -> {
           return bossQueryService.queryCity();
       });
       Future<List<Positions>> positionFuture = getInfoPool.submit(() -> {
           return bossQueryService.queryLeafPositions();
       });
       List<City> citys = new ArrayList<>();
       List<Positions> positions = new ArrayList<>();
      try{
           citys =  cityFuture.get();
          positions = positionFuture.get();
      }catch (Exception e){}

      if( !CollectionUtils.isEmpty(citys) && !CollectionUtils.isEmpty(positions)){
        for(City city:citys){
            for(Positions position:positions){
                String apiUrl = "";


            }
        }
      }




   }

}
