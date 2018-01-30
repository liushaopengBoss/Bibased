package cn.edu.zzti.bibased.service.operation.lagou;

import cn.edu.zzti.bibased.dao.lagou.LagouDao;
import cn.edu.zzti.bibased.dto.City;
import cn.edu.zzti.bibased.dto.Company;
import cn.edu.zzti.bibased.dto.Positions;
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
     * 批量数据写入公司信息
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public void batchAddCompany(List<Company> companies){
        lagouDao.batchInsertCompanys(companies);
    }
}
