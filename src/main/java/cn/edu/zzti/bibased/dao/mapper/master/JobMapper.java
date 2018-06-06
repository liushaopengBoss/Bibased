package cn.edu.zzti.bibased.dao.mapper.master;

import cn.edu.zzti.bibased.dto.Job;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface JobMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Job record);

    int insertSelective(Job record);

    List<Job> queryAllJobs();

    int updateByPrimaryKeySelective(Job record);

    int updateByPrimaryKey(Job record);

}