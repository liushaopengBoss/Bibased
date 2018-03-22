package cn.edu.zzti.bibased.dao.lagou;

import cn.edu.zzti.bibased.constant.WebsiteEnum;
import cn.edu.zzti.bibased.dao.cache.Cache;
import cn.edu.zzti.bibased.dao.mapper.*;
import cn.edu.zzti.bibased.dto.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用于数据的写入
 */
@Repository
public class LagouDao {
    @Resource
    private PositionsMapper positionsMapper;

    @Resource
    private CityMapper cityMapper;

    @Resource
    private CompanyMapper companyMapper;

    @Resource
    private PositionDetailMapper positionDetailMapper;

    @Resource
    private ActionLogDao actionLogDao;

    @Resource
    private Cache cache;

    public void insertJob(Positions position){
        positionsMapper.insert(position);
    }
    public void batchInsertJobs(List<Positions> positionList){
        positionsMapper.batchInsert(positionList);
    }

    public void batchInsertCitys(List<City> cityList){
        cityMapper.batchInsert(cityList);
    }

    public void batchInsertCompanys(List<Company> companies){
        companyMapper.batchInsert(companies);
    }

    public List<Positions> queryLeafPositions(String include){
        return positionsMapper.queryLeafPositions(include);
    }

    public List<City> queryCitys(String include){
        return cityMapper.queryCity(include);
    }

    public void batchInsertPositionDetails(List<PositionDetail> positionDetails){
        positionDetailMapper.batchInsert(positionDetails);
    }

    public Long queryLastPositionCreateTime(String city,String thirdType){
        Long positionDetail = positionDetailMapper.selectLastPostionCreateTime(WebsiteEnum.LAGOU.getWebCode(),city,thirdType);
        return positionDetail !=null?positionDetail:0L;
    }
    private List<ActionLogDO> queryActionLog(int typeCode){
        return actionLogDao.queryActionLog(WebsiteEnum.LAGOU.getWebCode(),typeCode);
    }
    /**
     * 1.分析各个城市的公司数量 去重
     */
    public List<Company> queryCityCompanNum(){
        if(cache.containsKey(WebsiteEnum.LAGOU.getWebCode()+"queryCityCompanNum")){
            return (List<Company>)cache.getValue(WebsiteEnum.LAGOU.getWebCode()+"queryCityCompanNum");
        }else{
            List<Company> companies = companyMapper.queryCityCompanNum(WebsiteEnum.LAGOU.getWebCode());
            cache.add(WebsiteEnum.LAGOU.getWebCode()+"queryCityCompanNum",companies);
            return companies;
        }
    }
    /**
     * 1.各个城市融资情况公司数量
     */
    public List<Company> queryFinanceStageCompanNum(){
        if(cache.containsKey(WebsiteEnum.LAGOU.getWebCode()+"queryFinanceStageCompanNum")){
            return (List<Company>)cache.getValue(WebsiteEnum.LAGOU.getWebCode()+"queryFinanceStageCompanNum");
        }else{
            List<Company> companies = companyMapper.queryFinanceStageCompanNum(WebsiteEnum.LAGOU.getWebCode());
            cache.add(WebsiteEnum.LAGOU.getWebCode()+"queryFinanceStageCompanNum",companies);
            return companies;
        }
    }
    /**
     * 1.分析各个城市行业领域公司数量 去重
     */
    public List<Company> queryIndustryCompanNum(){
        if(cache.containsKey(WebsiteEnum.LAGOU.getWebCode()+"queryIndustryCompanNum")){
            return (List<Company>)cache.getValue(WebsiteEnum.LAGOU.getWebCode()+"queryIndustryCompanNum");
        }else{
            List<Company> companies = companyMapper.queryIndustryCompanNum(WebsiteEnum.LAGOU.getWebCode());
            cache.add(WebsiteEnum.LAGOU.getWebCode()+"queryIndustryCompanNum",companies);
            return companies;
        }
    }
    /**
     * 查询职位类型数据统计
     *
     * @return
     */
    public List<Positions> queryPositionTypeNums(){
        return positionsMapper.queryPositionTypeNums();
    }

    public List<PositionDetail> queryWorkYearNums(){
        if(cache.containsKey(WebsiteEnum.LAGOU.getWebCode()+"queryWorkYearNums")){
            return (List<PositionDetail>)cache.getValue(WebsiteEnum.LAGOU.getWebCode()+"queryWorkYearNums");
        }else{
            List<PositionDetail> positionDetails = positionDetailMapper.queryWorkYearNums(WebsiteEnum.LAGOU.getWebCode());
            cache.add(WebsiteEnum.LAGOU.getWebCode()+"queryWorkYearNums",positionDetails);
            return positionDetails;
        }
    }

    public List<PositionDetail> queryEducationNums(){
        if(cache.containsKey(WebsiteEnum.LAGOU.getWebCode()+"queryEducationNums")){
            return (List<PositionDetail>)cache.getValue(WebsiteEnum.LAGOU.getWebCode()+"queryEducationNums");
        }else{
            List<PositionDetail> positionDetails =  positionDetailMapper.queryEducationNums(WebsiteEnum.LAGOU.getWebCode());
            cache.add(WebsiteEnum.LAGOU.getWebCode()+"queryEducationNums",positionDetails);
            return positionDetails;
        }
    }

    public List<PositionDetail> queryJobNatureNums(){
        if(cache.containsKey(WebsiteEnum.LAGOU.getWebCode()+"queryJobNatureNums")){
            return (List<PositionDetail>)cache.getValue(WebsiteEnum.LAGOU.getWebCode()+"queryJobNatureNums");
        }else{
            List<PositionDetail> positionDetails = positionDetailMapper.queryJobNatureNums(WebsiteEnum.LAGOU.getWebCode());
            cache.add(WebsiteEnum.LAGOU.getWebCode()+"queryJobNatureNums",positionDetails);
            return positionDetails;
        }
    }
    public List<PositionDetail> queryPositionDetailsByFirstTye(String firstType){
        if(cache.containsKey(WebsiteEnum.LAGOU.getWebCode()+"queryPositionDetailsByFirstTye")){
            return (List<PositionDetail>)cache.getValue(WebsiteEnum.LAGOU.getWebCode()+"queryPositionDetailsByFirstTye");
        }else{
            List<PositionDetail> positionDetails = positionDetailMapper.queryPositionDetailsByFirstTye(WebsiteEnum.LAGOU.getWebCode(),firstType);
            cache.add(WebsiteEnum.LAGOU.getWebCode()+"queryPositionDetailsByFirstTye",positionDetails);
            return positionDetails;
        }
    }

    public List<PositionDetail> queryCompanySize(){
        if(cache.containsKey(WebsiteEnum.LAGOU.getWebCode()+"queryCompanySize")){
            return (List<PositionDetail>)cache.getValue(WebsiteEnum.LAGOU.getWebCode()+"queryCompanySize");
        }else{
            List<PositionDetail> positionDetails = positionDetailMapper.queryCompanySize(WebsiteEnum.LAGOU.getWebCode());
            cache.add(WebsiteEnum.LAGOU.getWebCode()+"queryCompanySize",positionDetails);
            return positionDetails;
        }
    }
}
