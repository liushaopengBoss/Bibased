package cn.edu.zzti.bibased.dao.mapper;


import cn.edu.zzti.bibased.dto.PositionKeyword;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PositionKeywordMapper {

    /**
     * 添加数据 有则更新，无则添加
     *
     *
     * @param keyword
     */
    @Insert("      REPLACE into position_keyword set  curr_date =#{currDate},\n" +
            "      keyword_name =  #{keywordName}, keyword_num = keyword_num+#{keywordNum}, position_type = #{positionType}, \n" +
            "      include =  #{include}, create_time = UNIX_TIMESTAMP(now()), op_time = UNIX_TIMESTAMP(now()), \n" +
            "      is_valid=1, last_ver = last_ver+1")
    void repaceAddPositionKeyWord(PositionKeyword keyword);
    /**
     * 通过日期和职位类型查找某一天的关键字
     *  写到controller层
     *
     * @param currDate 日期
     * @return positionType  职位类型
     */
    @Select(" SELECT curr_date as currDate, keyword_name as keywordName ,keyword_num as keywordNum from position_keyword \n" +
            "                  where curr_date = #{currDate}  and position_type = #{positionType}\n" +
            "                   GROUP BY keyword_name ORDER BY num DESC")
    List<PositionKeyword> queryPositionKeyWordByCurrrDate(@Param("currDate")String currDate,@Param("positionType")String positionType);

    /**
     * 查询日期范围内 某种职位类型关键词出现的次数并且排序 order by
     * 写到controller
     *
     *
     * @param startDate  开始日期
     * @param endDate  结束日期
     * @param positionType  职位类型
     * @return
     */
    @Select(
            " SELECT keyword_name, SUM(keyword_num) AS keywordNum\n" +
            " FROM  position_keyword\n" +
            " WHERE curr_date >= #{startDate}\n" +
            " AND curr_date <= #{endDate}\n" +
            " AND position_type = #{positionType}\n" +
            " GROUP BY keyword_name \n" +
            " ORDER BY keywordNum DESC  limit 20")
    List<PositionKeyword>  queryPositionKeyWordNumsByDateRangeAndPosition(@Param("startDate")String startDate,@Param("endDate")String endDate,
                                                                                  @Param("positionType")String positionType, @Param("include")String include);

}