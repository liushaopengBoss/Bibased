package cn.edu.zzti.bibased.dao.zhilian;

import cn.edu.zzti.bibased.constant.WebsiteEnum;
import cn.edu.zzti.bibased.dao.mapper.PositionDetailMapper;
import cn.edu.zzti.bibased.dto.PositionDetail;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ZhilianDao {

    @Resource
    private PositionDetailMapper positionDetailMapper;

    public List<PositionDetail> queryPositionDetailsByFirstTye(String firstType){
        return positionDetailMapper.queryPositionDetailsByFirstTye(WebsiteEnum.ZHILIAN.getWebCode(),firstType);
    }
    public List<PositionDetail> queryWorkYearNums(){
        return positionDetailMapper.queryWorkYearNums(WebsiteEnum.ZHILIAN.getWebCode());
    }

    public List<PositionDetail>   queryEducationNums(){
        return positionDetailMapper.queryEducationNums(WebsiteEnum.ZHILIAN.getWebCode());
    }

    public List<PositionDetail>  queryJobNatureNums(){
        return positionDetailMapper.queryJobNatureNums(WebsiteEnum.ZHILIAN.getWebCode());
    }

}
