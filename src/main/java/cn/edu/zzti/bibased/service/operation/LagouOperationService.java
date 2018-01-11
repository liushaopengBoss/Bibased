package cn.edu.zzti.bibased.service.operation;

import cn.edu.zzti.bibased.dao.write.dao.LagouWriteDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class LagouOperationService {

    @Resource
    private LagouWriteDao lagouWriteDao;
    /**
     * 单个数据写入
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public void add(){

    }

    /**
     * 批量数据写入
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public void batchAdd(){

    }
}
