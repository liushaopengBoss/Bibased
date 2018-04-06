package cn.edu.zzti.bibased.dao.boss;

import cn.edu.zzti.bibased.constant.WebsiteEnum;
import cn.edu.zzti.bibased.dao.mapper.CityMapper;
import cn.edu.zzti.bibased.dao.mapper.PositionsMapper;
import cn.edu.zzti.bibased.dto.City;
import cn.edu.zzti.bibased.dto.Positions;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class BossDao {

    @Resource
    private CityMapper cityMapper;
    @Resource
    private PositionsMapper positionsMapper;

    public List<City> getCitys(){
        return cityMapper.queryCity(WebsiteEnum.BOSS.getWebCode());
    }

    /**
     * 获取叶子节点
     * @return
     */
    public List<Positions> getLeafPositions(){
        return positionsMapper.queryLeafPositions(WebsiteEnum.BOSS.getWebCode());
    }
}
