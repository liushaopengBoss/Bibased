package cn.edu.zzti.bibased.dto;

import java.io.Serializable;

/**
 * zhi
 * <p>
 * Created by huaidou on  2018/1/11
 */
public class Jobs implements Serializable {
    /**
     *   `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
     `job_url` varchar(50) DEFAULT NULL COMMENT '职位url链接地址',
     `job_name` varchar(50) DEFAULT NULL COMMENT '职位名称',
     `include` bigint(2) DEFAULT NULL COMMENT '属于哪个网站',
     `is_valid` tinyint(4) DEFAULT NULL COMMENT '是否有效',
     `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
     `op_time` bigint(20) DEFAULT NULL COMMENT '修改时间',
     */
    private int id;
    private String jobUrl;
    private String jobName;
    private int include;
    private long createTime;
    private long opTime;
    private long lastVer;
    private int isValid;
    private int parentId;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobUrl() {
        return jobUrl;
    }

    public void setJobUrl(String jobUrl) {
        this.jobUrl = jobUrl;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getInclude() {
        return include;
    }

    public void setInclude(int include) {
        this.include = include;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getOpTime() {
        return opTime;
    }

    public void setOpTime(long opTime) {
        this.opTime = opTime;
    }

    public long getLastVer() {
        return lastVer;
    }

    public void setLastVer(long lastVer) {
        this.lastVer = lastVer;
    }

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

}
