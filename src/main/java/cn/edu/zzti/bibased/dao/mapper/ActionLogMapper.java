package cn.edu.zzti.bibased.dao.mapper;

import cn.edu.zzti.bibased.dto.ActionLogDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActionLogMapper {

    void insert(ActionLogDO actionLog);

    List<ActionLogDO> queryActionLog(@Param("include")String include,@Param("typeCode")int typeCode);

}
