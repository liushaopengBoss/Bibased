package cn.edu.zzti.bibased.dao.lagou;

import cn.edu.zzti.bibased.dao.mapper.CityMapper;
import cn.edu.zzti.bibased.dao.mapper.JobsMapper;
import cn.edu.zzti.bibased.dto.City;
import cn.edu.zzti.bibased.dto.Jobs;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用于数据的写入
 */
@Repository
public class LagouDao {

    @Resource
    private JobsMapper jobsMapper;

    @Resource
    private CityMapper cityMapper;

    public void insertJob(Jobs job){
        jobsMapper.insert(job);
    }
    public void batchInsertJobs(List<Jobs> jobList){
        jobsMapper.batchInsert(jobList);
    }

    public void batchInsertCitys(List<City> cityList){
        cityMapper.batchInsert(cityList);
    }
}
