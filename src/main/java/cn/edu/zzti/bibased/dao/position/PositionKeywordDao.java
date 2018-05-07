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

}
