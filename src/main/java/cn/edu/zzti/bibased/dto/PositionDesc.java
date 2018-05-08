package cn.edu.zzti.bibased.dto;

public class PositionDesc {
    private Integer id;

    private Integer positionId;

    private String currDate;

    private String postDuties;

    private String tenureRequirements;

    private String positionType;

    private String include;

    private Long createTime;

    private Long opTime;

    private Byte isValid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getPostDuties() {
        return postDuties;
    }

    public void setPostDuties(String postDuties) {
        this.postDuties = postDuties == null ? null : postDuties.trim();
    }

    public String getTenureRequirements() {
        return tenureRequirements;
    }

    public void setTenureRequirements(String tenureRequirements) {
        this.tenureRequirements = tenureRequirements == null ? null : tenureRequirements.trim();
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType == null ? null : positionType.trim();
    }

    public String getInclude() {
        return include;
    }

    public void setInclude(String include) {
        this.include = include == null ? null : include.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getOpTime() {
        return opTime;
    }

    public void setOpTime(Long opTime) {
        this.opTime = opTime;
    }

    public Byte getIsValid() {
        return isValid;
    }

    public void setIsValid(Byte isValid) {
        this.isValid = isValid;
    }

    public String getCurrDate() {
        return currDate;
    }

    public void setCurrDate(String currDate) {
        this.currDate = currDate;
    }

    @Override
    public String toString() {
        return "PositionDesc{" +
                "id=" + id +
                ", positionId=" + positionId +
                ", currDate='" + currDate + '\'' +
                ", postDuties='" + postDuties + '\'' +
                ", tenureRequirements='" + tenureRequirements + '\'' +
                ", positionType='" + positionType + '\'' +
                ", include='" + include + '\'' +
                ", createTime=" + createTime +
                ", opTime=" + opTime +
                ", isValid=" + isValid +
                '}';
    }
}