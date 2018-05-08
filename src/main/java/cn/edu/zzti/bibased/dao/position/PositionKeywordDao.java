package cn.edu.zzti.bibased.dao.position;

import cn.edu.zzti.bibased.dao.mapper.PositionKeywordMapper;
import cn.edu.zzti.bibased.dto.PositionKeyword;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PositionKeywordDao {

    @Resource
    private PositionKeywordMapper positionKeywordMapper;

    public void batchAddPositionKeyWord(List<PositionKeyword> positionKeyword) {
        if(!CollectionUtils.isEmpty(positionKeyword)){
            for(int i=0;i<positionKeyword.size();i++)
            positionKeywordMapper.repaceAddPositionKeyWord(positionKeyword.get(i));
        }

    }


    public List<String> queryPositionTypes(){
        return positionKeywordMapper.queryPositionTypes();
    }

    public List<PositionKeyword> queryPositionKeyWordByCurrrDate(String currDate,String positionType){
        return positionKeywordMapper.queryPositionKeyWordByCurrrDate(currDate,positionType);
    }

    public List<PositionKeyword>  queryPositionKeyWordNumsByDateRangeAndPosition(String startDate,String endDate,
                                                                          String positionType, String include){
        return positionKeywordMapper.queryPositionKeyWordNumsByDateRangeAndPosition(startDate,endDate,positionType,include);
    }
}
