package cn.edu.zzti.bibased.dao.position;

import cn.edu.zzti.bibased.dao.mapper.master.PositionDescMapper;
import cn.edu.zzti.bibased.dto.PositionDesc;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class PositionDescDao {

    @Resource
    private PositionDescMapper positionDescMapper;

    public void batchPositionDesc(List<PositionDesc> positionDescs){
        positionDescMapper.batchPositionDetail(positionDescs);
    }

    public List<PositionDesc> getPositionDesc(String currDate,String positionType,int pageRow,int pageSize){
       return  positionDescMapper.getPositionDesc(currDate,positionType,pageRow,pageSize);
    }

    public Integer queryPositionDescCount(String currDate,String positionType){
        return positionDescMapper.queryPositionDescCount(currDate,positionType);
    }

    public List<String> queryPositionTypes(String currDate){
        return positionDescMapper.queryPositionTypes(currDate);
    }
}
