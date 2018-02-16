package cn.edu.zzti.bibased.dao.mapper;

import cn.edu.zzti.bibased.dto.Positions;

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
}
