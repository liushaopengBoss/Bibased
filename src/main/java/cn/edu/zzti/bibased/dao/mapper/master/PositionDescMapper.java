package cn.edu.zzti.bibased.dao.mapper.master;


import cn.edu.zzti.bibased.dto.PositionDesc;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PositionDescMapper {

   void batchPositionDetail(List<PositionDesc> positionDescs);

    /**
     * 通过分页查询职位描述
     *
     * @param currDate
     * @param pageRow
     * @param pageSize
     * @return
     */
    @Select("select position_id as positionId, curr_date as currDate, post_duties as  postDuties, tenure_requirements as tenureRequirements, position_type as  positionType from position_desc where curr_date = #{currDate} and position_type = #{positionType}  limit #{pageRow},#{pageSize}")
     List<PositionDesc> getPositionDesc(@Param("currDate")String currDate,@Param("positionType")String positionType,@Param("pageRow")int pageRow,@Param("pageSize")int pageSize);

    /**
     * 查询出某天职位描述的数量
     *
     * @param currDate
     * @return
     */
    @Select("select count(id) from position_desc where curr_date = #{currDate} and position_type = #{positionType}")
     Integer queryPositionDescCount(@Param("currDate")String currDate,@Param("positionType")String positionType);

 /**
  * 查询出某天职位描述的职位类型
  * eg: Java PHP Python
  *
  * @param currDate
  * @return
  */
 @Select("select position_type from position_desc where curr_date = #{currDate} group by position_type")
 List<String> queryPositionTypes(@Param("currDate")String currDate);


}