package cn.edu.zzti.bibased.dao.mapper;

import cn.edu.zzti.bibased.dto.Jobs;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用途
 * <p>
 * Created by huaidou on  2018/1/11
 */
public interface JobsMapper {
    /**
     * 插入
     * @param jobs
     */
     void insert(Jobs jobs);

    /**
     * 批量插入
     * @param jobList
     */
     void batchInsert(List<Jobs> jobList);
}
