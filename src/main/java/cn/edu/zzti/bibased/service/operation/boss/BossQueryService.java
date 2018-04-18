package cn.edu.zzti.bibased.service.operation.boss;

import cn.edu.zzti.bibased.dao.boss.BossDao;
import cn.edu.zzti.bibased.dto.City;
import cn.edu.zzti.bibased.dto.PositionDetail;
import cn.edu.zzti.bibased.dto.Positions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BossQueryService {
    @Resource
    private BossDao bossDao;

    public List<City> queryCity(){
        return bossDao.getCitys();
    }

    public List<Positions> queryLeafPositions(){
        return bossDao.getLeafPositions();
    }


    public List<PositionDetail> queryIndustryFieldNums(){
        return bossDao.queryIndustryFieldNums();
    }


    public List<PositionDetail>  queryFinanceStage(){
        return bossDao.queryFinanceStage();
    }

    public List<PositionDetail>   queryCompanySize(){
        return bossDao.queryCompanySize();
    }

    public List<PositionDetail>  queryJobNatureNums(){
        return bossDao.queryJobNatureNums();
    }


    public List<PositionDetail>   queryEducationNums(){
        return bossDao.queryEducationNums();
    }

    public List<PositionDetail> queryWorkYearNums(){
        return bossDao.queryWorkYearNums();
    }

}
