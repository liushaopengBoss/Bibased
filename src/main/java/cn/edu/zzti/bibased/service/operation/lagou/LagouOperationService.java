package cn.edu.zzti.bibased.service.operation.lagou;

import cn.edu.zzti.bibased.dao.lagou.LagouDao;
import cn.edu.zzti.bibased.dto.City;
import cn.edu.zzti.bibased.dto.Position;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
public class LagouOperationService{

    @Resource
    private LagouDao lagouDao;
    /**
     * 单个数据写入
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public void addJob(Position jobs){
        lagouDao.insertJob(jobs);
    }

    /**
     * 批量数据写入
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public void batchAddJob(List<Position> jobs){
        lagouDao.batchInsertJobs(jobs);
    }


    /**
     * 批量数据写入
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public void batchAddCity(List<City> city){
        lagouDao.batchInsertCitys(city);
    }
}
