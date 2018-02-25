package cn.edu.zzti.bibased.dao.lagou;

import cn.edu.zzti.bibased.constant.WebsiteEnum;
import cn.edu.zzti.bibased.dao.mapper.ActionLogMapper;
import cn.edu.zzti.bibased.dto.ActionLogDO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ActionLogDao {
    @Resource
    protected ActionLogMapper actionLogMapper;

    public void insert(ActionLogDO actionLogDO){
        actionLogMapper.insert(actionLogDO);
    }
    public List<ActionLogDO> queryActionLog(String include,int typeCode){
        return actionLogMapper.queryActionLog(include,typeCode);
    }


}
