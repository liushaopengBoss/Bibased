package cn.edu.zzti.bibased.service.operation.zhilian;

import cn.edu.zzti.bibased.dao.zhilian.ZhilianDao;
import cn.edu.zzti.bibased.dto.PositionDetail;
import cn.edu.zzti.bibased.utils.ZkLockUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ZhilianQueryService {

    @Resource
    private ZhilianDao zhilianDao;
    public List<PositionDetail> queryPositionDetailsByFirstTye(String firstType){
        return zhilianDao.queryPositionDetailsByFirstTye(firstType);
    }
    public List<PositionDetail> queryWorkYearNums(){
        boolean res=false;
        try{
            res = ZkLockUtil.acquire(3, TimeUnit.SECONDS);
            return zhilianDao.queryWorkYearNums();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(res){//释放锁
                ZkLockUtil.release();
            }
        }
        return null;
    }

    public List<PositionDetail>   queryEducationNums(){
        return zhilianDao.queryEducationNums();
    }
    public List<PositionDetail>  queryJobNatureNums(){
        return zhilianDao.queryJobNatureNums();
    }
}
