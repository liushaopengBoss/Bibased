package cn.edu.zzti.bibased.dao.mapper;

import cn.edu.zzti.bibased.dto.PositionDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 职位详细信息mapper
 *
 * Created by huaidou on  2018/1/15
 */
public interface PositionDetailMapper {

    void insert(PositionDetail positionDetail);

    void batchInsert(List<PositionDetail> positionDetails);

    PositionDetail selectByPrimaryKey(Integer id);

    Long selectLastPostionCreateTime(@Param("include") String include, @Param("city") String city, @Param("thirdType") String thirdType);

}