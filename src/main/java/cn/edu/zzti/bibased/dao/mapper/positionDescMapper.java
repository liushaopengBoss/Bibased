package cn.edu.zzti.bibased.dao.mapper;


import cn.edu.zzti.bibased.dto.PositionDesc;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface positionDescMapper {
    /**
     * 通过分页查询职位描述
     *
     * @param currDate
     * @param pageRow
     * @param pageSize
     * @return
     */
    @Select("")
     List<PositionDesc> getPositionDesc(@Param("currDate")String currDate,@Param("pageRow")int pageRow,@Param("pageRow")int pageSize);

    /**
     * 查询出某天职位描述的数量
     *
     * @param currDate
     * @return
     */
    @Select("")
     Integer queryPositionDescCount(@Param("currDate")String currDate);
}