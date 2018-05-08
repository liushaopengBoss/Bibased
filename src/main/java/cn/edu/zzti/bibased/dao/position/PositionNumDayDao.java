package cn.edu.zzti.bibased.dao.position;

import cn.edu.zzti.bibased.dao.mapper.PositionNumDayMapper;
import cn.edu.zzti.bibased.dto.PositionNumDay;
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

    public List<PositionNumDay> queryDateRangPositionNumDay(String startDate, String endDate,
                                                     String positionType){
        return positionNumDayMapper.queryDateRangPositionNumDay(startDate,endDate,positionType);
    }

    public PositionNumDay queryDateRangPositionNumDaySum(String startDate, String endDate, String positionType){
        return positionNumDayMapper.queryDateRangPositionNumDaySum(startDate,endDate,positionType);
    }
    public void addPositionTypes(PositionNumDay positionNumDay){
        positionNumDayMapper.addPositionTypes(positionNumDay);
    }

}
