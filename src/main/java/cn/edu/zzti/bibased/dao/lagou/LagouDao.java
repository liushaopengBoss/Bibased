package cn.edu.zzti.bibased.dao.lagou;

import cn.edu.zzti.bibased.constant.WebsiteEnum;
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

    public Long queryLastPositionCreateTime(){
        PositionDetail positionDetail = positionDetailMapper.selectLastPostionCreateTime(WebsiteEnum.LAGOU.getWebCode());
        return positionDetail !=null?positionDetail.getPositionCreateTime():0L;
    }
    private List<ActionLogDO> queryActionLog(int typeCode){
        return actionLogDao.queryActionLog(WebsiteEnum.LAGOU.getWebCode(),typeCode);
    }

}
