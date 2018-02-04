package cn.edu.zzti.bibased.service.operation.lagou;

import cn.edu.zzti.bibased.constant.WebsiteEnum;
import cn.edu.zzti.bibased.dao.lagou.LagouDao;
import cn.edu.zzti.bibased.dto.City;
import cn.edu.zzti.bibased.dto.Company;
import cn.edu.zzti.bibased.dto.Positions;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
public class LagouOperationService{
    private Logger logger = LoggerFactory.getLogger(LagouOperationService.class);
    @Resource
    private LagouDao lagouDao;

    /**
     * 批量数据写入职位
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public void batchAddJob(List<Positions> jobs){
        lagouDao.batchInsertJobs(jobs);
    }


    /**
     * 批量数据写入城市
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public void batchAddCity(List<City> city){
        lagouDao.batchInsertCitys(city);
    }


    /**
     * 批量数据写入公司信息  并且异步操作
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Async
    public void batchAddCompany(List<Company> companies){
        StopWatch clock = new StopWatch();
        clock.start(); //计时开始
        lagouDao.batchInsertCompanys(companies);
        clock.stop();
        long time = clock.getTime();
        logger.info("批处理执行时间:"+time+"\n");
    }

    public List<Positions> queryLeftPositions(){
        return  lagouDao.queryLeafPositions(WebsiteEnum.LAGOU.getWebCode());
    }

    public List<City> queryCitys(){
        return lagouDao.queryCitys(WebsiteEnum.LAGOU.getWebCode());
    }
}
