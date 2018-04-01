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
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class LagouQueryService {
    @Resource
    private LagouDao lagouDao;
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

    public List<PositionDetail> queryCompanySize(){
        return lagouDao.queryCompanySize();
    }

    public Map<String,List<PositionDetail>> queryWebCityNums(){
        Map<String,List<PositionDetail>> mapResult = new HashMap<>();
        List<PositionDetail> positionDetails = lagouDao.queryWebCityNums();
        for(WebsiteEnum websiteEnum:WebsiteEnum.values()){
            List<PositionDetail> collect = positionDetails.stream().filter(positionDetail -> {
                return positionDetail.getInclude().equals(websiteEnum.getWebCode());
            }).collect(Collectors.toList());
            mapResult.put(websiteEnum.getWebCode(),collect);
        }
        return mapResult;
    }
}
