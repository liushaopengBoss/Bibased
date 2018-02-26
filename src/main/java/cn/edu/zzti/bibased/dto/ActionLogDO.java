package cn.edu.zzti.bibased.dto;

import java.io.Serializable;

public class ActionLogDO implements Serializable {
    private int status;
    private String startTime;
    private String endTime;
    private String actionName;
    private String include;
    private int typeCode;
    private long createTime;//创建时间
    private long opTime;//修改时间
    private long lastVer;// 版本
    private int isValid;//是否有效

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
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

    public String getInclude() {
        return include;
    }

    public void setInclude(String include) {
        this.include = include;
    }

    @Override
    public String toString() {
        return "ActionLogDO{" +
                "status=" + status +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", actionName='" + actionName + '\'' +
                ", include='" + include + '\'' +
                ", typeCode=" + typeCode +
                ", createTime=" + createTime +
                ", opTime=" + opTime +
                ", lastVer=" + lastVer +
                ", isValid=" + isValid +
                '}';
    }
}
