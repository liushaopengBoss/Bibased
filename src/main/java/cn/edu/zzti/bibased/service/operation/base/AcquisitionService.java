package cn.edu.zzti.bibased.service.operation.base;


import cn.edu.zzti.bibased.constant.WebsiteEnum;
import cn.edu.zzti.bibased.dao.acquisition.AcquisitionDao;
import cn.edu.zzti.bibased.dto.City;
import cn.edu.zzti.bibased.dto.Company;
import cn.edu.zzti.bibased.dto.PositionDetail;
import cn.edu.zzti.bibased.dto.Positions;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 信息采集
 *
 * Created by huaidou on  2018/3/27
 */
@Service
public class AcquisitionService {
    Logger logger = LoggerFactory.getLogger(AcquisitionService.class);
    @Resource
    private AcquisitionDao acquisitionDao;
    /**
     * 批量数据写入职位
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public void batchAddJob(List<Positions> jobs){
        if(!CollectionUtils.isEmpty(jobs)){
            acquisitionDao.batchInsertJobs(jobs);
        }
    }


    /**
     * 批量数据写入城市
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public void batchAddCity(List<City> city){
        if(!CollectionUtils.isEmpty(city)){
            acquisitionDao.batchInsertCitys(city);
        }
    }


    /**
     * 批量数据写入公司信息  并且异步操作
     */
    @Async
    public void batchAddCompany(List<Company> companies){
        if(!CollectionUtils.isEmpty(companies)){
            StopWatch clock = new StopWatch();
            clock.start(); //计时开始
            acquisitionDao.batchInsertCompanys(companies);
            clock.stop();
            long time = clock.getTime();
            logger.info("批处理执行时间:"+time+"\n"+"数量：:"+companies.size());
        }

    }

    /**
     * 批量数据写入职位详情信息  并且异步操作
     */
    @Async
    public void batchAddPositionDetails(List<PositionDetail> positionDetails){
        if(!CollectionUtils.isEmpty(positionDetails)){
            StopWatch clock = new StopWatch();
            clock.start(); //计时开始
            acquisitionDao.batchInsertPositionDetails(positionDetails);
            clock.stop();
            long time = clock.getTime();
            logger.info("批处理执行时间:"+time+"\n");
        }

    }

    public List<Positions> queryLeftPositions(){
        return  acquisitionDao.queryLeafPositions(WebsiteEnum.LAGOU.getWebCode());
    }

    public List<City> queryCitys(){
        return acquisitionDao.queryCitys(WebsiteEnum.LAGOU.getWebCode());
    }

}
