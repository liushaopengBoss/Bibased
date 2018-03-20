package cn.edu.zzti.bibased.service.operation.lagou;

import cn.edu.zzti.bibased.constant.WebsiteEnum;
import cn.edu.zzti.bibased.dao.lagou.LagouDao;
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
    @Async
    public void batchAddCompany(List<Company> companies){
        StopWatch clock = new StopWatch();
        clock.start(); //计时开始
        lagouDao.batchInsertCompanys(companies);
        clock.stop();
        long time = clock.getTime();
        logger.info("批处理执行时间:"+time+"\n"+"数量：:"+companies.size());
    }

    /**
     * 批量数据写入职位详情信息  并且异步操作
     */
    @Async
    public void batchAddPositionDetails(List<PositionDetail> positionDetails){
        StopWatch clock = new StopWatch();
        clock.start(); //计时开始
        lagouDao.batchInsertPositionDetails(positionDetails);
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

    /**
     * 获取最后一起信息抓取的时间
     * @param city
     * @param thirdType
     * @return
     */
    public Long queryLastPositionCreateTime(String city,String thirdType){
        return lagouDao.queryLastPositionCreateTime(city,thirdType);
    }

    /**
     * 1.分析各个城市的公司数量 去重
     */
    public List<Company> queryCityCompanNum(){
        return lagouDao.queryCityCompanNum();
    }
    /**
     * 1.各个城市融资情况公司数量
     */
    public List<Company> queryFinanceStageCompanNum(){
        return lagouDao.queryFinanceStageCompanNum();
    }
    /**
     * 1.分析各个城市行业领域公司数量 去重
     */
    public List<Company> queryIndustryCompanNum(){
        return lagouDao.queryIndustryCompanNum();
    }
    /**
     * 分析 职位类型数据统计
     *
     * @return
     */
    public List<Positions> queryPositionTypeNums(){
        return lagouDao.queryPositionTypeNums();
    }

    /**
     * 分析各个工作年限职位的数量
     *
     * @return
     */
    public List<PositionDetail> queryWorkYearNums(){
        return lagouDao.queryWorkYearNums();
    }


    public List<PositionDetail> queryEducationNums(){
        return lagouDao.queryEducationNums();
    }

    public List<PositionDetail> queryJobNatureNums(){
        return lagouDao.queryJobNatureNums();
    }

    public List<PositionDetail> queryPositionDetailsByFirstTye(String firstType){
        return lagouDao.queryPositionDetailsByFirstTye(firstType);
    }
}
