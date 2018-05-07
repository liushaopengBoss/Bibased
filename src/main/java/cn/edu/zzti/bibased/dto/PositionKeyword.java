package cn.edu.zzti.bibased.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PositionKeyword {
    private Integer id;

    private Integer positionId;

    private String currDate;

    private String keywordName;

    private Integer keywordNum;

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

    public String getCurrDate() {
        return currDate;
    }

    public void setCurrDate(String currDate) {
        this.currDate = currDate == null ? null : currDate.trim();
    }

    public String getKeywordName() {
        return keywordName;
    }

    public void setKeywordName(String keywordName) {
        this.keywordName = keywordName == null ? null : keywordName.trim();
    }

    public Integer getKeywordNum() {
        return keywordNum;
    }

    public void setKeywordNum(Integer keywordNum) {
        this.keywordNum = keywordNum;
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
}