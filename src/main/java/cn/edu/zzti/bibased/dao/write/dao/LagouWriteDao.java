package cn.edu.zzti.bibased.dao.write.dao;

import cn.edu.zzti.bibased.dao.write.mapper.JobsMapper;
import cn.edu.zzti.bibased.dto.Jobs;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * 用于数据的写入
 */
@Repository
public class LagouWriteDao {

    @Resource
    private JobsMapper jobsMapper;

    public void insertJob(Jobs job){
        jobsMapper.insert(job);
    }
    public void batchInsertJobs(List<Jobs> jobList){
        jobsMapper.batchInsert(jobList);
    }
}
