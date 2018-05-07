package cn.edu.zzti.bibased.dao.position;

import cn.edu.zzti.bibased.dao.mapper.PositionNumDayMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class PositionNumDayDao {

    @Resource
    private PositionNumDayMapper positionNumDayMapper;


    public List<String> queryPositionTypes(){
        return positionNumDayMapper.queryPositionTypes();
    }


}
