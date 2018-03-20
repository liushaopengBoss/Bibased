package cn.edu.zzti.bibased.dao.lagou;

import cn.edu.zzti.bibased.constant.WebsiteEnum;
import cn.edu.zzti.bibased.dao.mapper.*;
import cn.edu.zzti.bibased.dto.*;
import org.apache.ibatis.annotations.Param;
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
        return companyMapper.queryCityCompanNum(WebsiteEnum.LAGOU.getWebCode());
    }
    /**
     * 1.各个城市融资情况公司数量
     */
    public List<Company> queryFinanceStageCompanNum(){
        return companyMapper.queryFinanceStageCompanNum(WebsiteEnum.LAGOU.getWebCode());
    }
    /**
     * 1.分析各个城市行业领域公司数量 去重
     */
    public List<Company> queryIndustryCompanNum(){
        return companyMapper.queryIndustryCompanNum(WebsiteEnum.LAGOU.getWebCode());
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
        return positionDetailMapper.queryWorkYearNums(WebsiteEnum.LAGOU.getWebCode());
    }

    public List<PositionDetail> queryEducationNums(){
        return positionDetailMapper.queryEducationNums(WebsiteEnum.LAGOU.getWebCode());
    }

    public List<PositionDetail> queryJobNatureNums(){
        return positionDetailMapper.queryJobNatureNums(WebsiteEnum.LAGOU.getWebCode());
    }
    public List<PositionDetail> queryPositionDetailsByFirstTye(String firstType){
        return positionDetailMapper.queryPositionDetailsByFirstTye(WebsiteEnum.LAGOU.getWebCode(),firstType);
    }
}
