package cn.edu.zzti.bibased.dao.mapper;

import cn.edu.zzti.bibased.dto.Position;

import java.util.List;

/**
 * 用途
 * <p>
 * Created by huaidou on  2018/1/11
 */
public interface PositionMapper {
    /**
     * 插入
     * @param jobs
     */
     void insert(Position jobs);

    /**
     * 批量插入
     * @param jobList
     */
     void batchInsert(List<Position> jobList);
}
