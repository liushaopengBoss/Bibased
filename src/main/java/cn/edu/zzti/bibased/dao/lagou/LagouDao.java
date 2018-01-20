package cn.edu.zzti.bibased.dao.lagou;

import cn.edu.zzti.bibased.dao.mapper.CityMapper;
import cn.edu.zzti.bibased.dao.mapper.PositionsMapper;
import cn.edu.zzti.bibased.dto.City;
import cn.edu.zzti.bibased.dto.Positions;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public void insertJob(Positions position){
        positionsMapper.insert(position);
    }
    public void batchInsertJobs(List<Positions> positionList){
        positionsMapper.batchInsert(positionList);
    }

    public void batchInsertCitys(List<City> cityList){
        cityMapper.batchInsert(cityList);
    }
}
