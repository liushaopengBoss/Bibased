package cn.edu.zzti.bibased.dao.mapper;


import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PositionNumDayMapper {

    @Select("select position_type  from position_num_day group by position_type")
    List<String> queryPositionTypes();

}