package cn.edu.zzti.bibased.dto;

public class Company {
    private Integer id;

    private String companyName;

    private String companyUrl;

    private String industryField;

    private String financeStage;

    private Integer positionNum;

    private Integer resumeRate;

    private String positionSlogan;

    private String include;

    private Long createTime;

    private Long opTime;

    private Byte isValid;

    private Long lastVer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl == null ? null : companyUrl.trim();
    }

    public String getIndustryField() {
        return industryField;
    }

    public void setIndustryField(String industryField) {
        this.industryField = industryField == null ? null : industryField.trim();
    }

    public String getFinanceStage() {
        return financeStage;
    }

    public void setFinanceStage(String financeStage) {
        this.financeStage = financeStage == null ? null : financeStage.trim();
    }

    public Integer getPositionNum() {
        return positionNum;
    }

    public void setPositionNum(Integer positionNum) {
        this.positionNum = positionNum;
    }

    public Integer getResumeRate() {
        return resumeRate;
    }

    public void setResumeRate(Integer resumeRate) {
        this.resumeRate = resumeRate;
    }

    public String getPositionSlogan() {
        return positionSlogan;
    }

    public void setPositionSlogan(String positionSlogan) {
        this.positionSlogan = positionSlogan == null ? null : positionSlogan.trim();
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

    public Long getLastVer() {
        return lastVer;
    }

    public void setLastVer(Long lastVer) {
        this.lastVer = lastVer;
    }
}