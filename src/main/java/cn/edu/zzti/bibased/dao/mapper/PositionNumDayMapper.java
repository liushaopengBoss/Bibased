package cn.edu.zzti.bibased.dao.mapper;


import cn.edu.zzti.bibased.dto.PositionNumDay;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PositionNumDayMapper {

    @Select("select position_type  from position_num_day group by position_type")
    List<String> queryPositionTypes();

    /**
     * 时间范围内某种职位类型数据统计之和
     *
     *
     * @param startDate
     * @param endDate
     * @param positionType
     * @return
     */
    @Select(
            " SELECT position_type as positionType ,SUM(position_num)  as positionNum FROM position_num_day  \n" +
                    " WHERE curr_date >= #{startDate}\n" +
                    " AND curr_date <= #{endDate}\n" +
                    " AND position_type = #{positionType}\n" +
                    " AND is_valid = 1")
    PositionNumDay queryDateRangPositionNumDaySum(@Param("startDate")String startDate, @Param("endDate")String endDate,
                                                        @Param("positionType")String positionType);

    /**
     * 时间范围内某种职位类型数据
     *
     *
     * @param startDate
     * @param endDate
     * @param positionType
     * @return
     */
    @Select(" SELECT curr_date as currDate, position_type as positionType ,position_num as positionNum from position_num_day \n" +
            " WHERE curr_date >= #{startDate}\n" +
            " AND curr_date <= #{endDate}\n" +
            " AND position_type = #{positionType}\n" +
            " AND is_valid = 1")
    List<PositionNumDay> queryDateRangPositionNumDay(@Param("startDate")String startDate, @Param("endDate")String endDate,
                                                        @Param("positionType")String positionType);
}