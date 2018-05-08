package cn.edu.zzti.bibased.service.operation.zhilian;

import cn.edu.zzti.bibased.dao.zhilian.ZhilianDao;
import cn.edu.zzti.bibased.dto.PositionDetail;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ZhilianQueryService {

    @Resource
    private ZhilianDao zhilianDao;
    public List<PositionDetail> queryPositionDetailsByFirstTye(String firstType){
        return zhilianDao.queryPositionDetailsByFirstTye(firstType);
    }
}
