package cn.edu.zzti.bibased.dao.mapper.master;

import cn.edu.zzti.bibased.dto.Positions;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用途
 * <p>
 * Created by huaidou on  2018/1/11
 */
public interface PositionsMapper {
    /**
     * 插入
     * @param position
     */
     void insert(Positions position);

    /**
     * 批量插入
     * @param positionList
     */
     void batchInsert(List<Positions> positionList);

     List<Positions>  queryLeafPositions(String include);

    /**
     * 查询职位类型数据统计
     *
     * @return
     */
    @Select("  SELECT position_name as positionName ,count(root_id) as num from positions where root_id in (\n" +
            "\t\tSELECT root_id from positions where parent_id = 0 and include = #{include} AND date_version = (\n" +
            "\t\t\tSELECT date_version from positions where include = #{include} order by date_version desc limit 1\n" +
            "\t\t)\n" +
            ")  group by root_id")
     List<Positions> queryPositionTypeNums(String include);
}
