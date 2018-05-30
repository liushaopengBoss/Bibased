package cn.edu.zzti.bibased.dao.job;

import cn.edu.zzti.bibased.dao.mapper.JobMapper;
import cn.edu.zzti.bibased.dto.Job;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class JobDao {

    @Resource
    private JobMapper jobMapper;

    public List<Job> queryAllJobs(){
        return jobMapper.queryAllJobs();
    }

}
