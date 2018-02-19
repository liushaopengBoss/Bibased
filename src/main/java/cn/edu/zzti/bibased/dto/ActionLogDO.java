package cn.edu.zzti.bibased.dto;

import java.io.Serializable;

public class ActionLogDO implements Serializable {
    private int status;
    private String startDate;
    private String endDate;
    private String actionName;
    private int typeCode;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    @Override
    public String toString() {
        return "ActionLogDO{" +
                "status=" + status +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", actionName='" + actionName + '\'' +
                ", typeCode=" + typeCode +
                '}';
    }
}
