package cn.edu.zzti.bibased.service.job;

import cn.edu.zzti.bibased.dao.job.JobDao;
import cn.edu.zzti.bibased.dao.mapper.JobMapper;
import cn.edu.zzti.bibased.dto.Job;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class JobService {

    @Resource
    private JobDao jobDao;

    public List<Job> queryAllJobs(){
        return jobDao.queryAllJobs();
    }

}
