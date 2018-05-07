package cn.edu.zzti.bibased.service.operation.other;

import cn.edu.zzti.bibased.dao.position.PositionNumDayDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PositionNumDayService {

    @Resource
    private PositionNumDayDao positionNumDayDao;

    public List<String> queryPositionTypes(){
        return positionNumDayDao.queryPositionTypes();
    }

}
