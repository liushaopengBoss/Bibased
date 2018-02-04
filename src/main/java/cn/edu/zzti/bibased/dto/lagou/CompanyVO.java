package cn.edu.zzti.bibased.dto.lagou;

/**
 * 用途
 * <p>
 * Created by huaidou on  2018/1/30
 */
public class CompanyVO {

    private Integer companyId;
    private String companyFullName;
    private String companyShortName;
    private String companyLogo;
    private String city;
    private String industryField;
    private String companyFeatures;
    private String financeStage;
    private Integer interviewRemarkNum;
    private Integer positionNum;
    private Integer processRate;
    private Integer approve;
    private Integer countryScore;
    private Integer cityScore;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyFullName() {
        return companyFullName;
    }

    public void setCompanyFullName(String companyFullName) {
        this.companyFullName = companyFullName;
    }

    public String getCompanyShortName() {
        return companyShortName;
    }

    public void setCompanyShortName(String companyShortName) {
        this.companyShortName = companyShortName;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIndustryField() {
        return industryField;
    }

    public void setIndustryField(String industryField) {
        this.industryField = industryField;
    }

    public String getCompanyFeatures() {
        return companyFeatures;
    }

    public void setCompanyFeatures(String companyFeatures) {
        this.companyFeatures = companyFeatures;
    }

    public String getFinanceStage() {
        return financeStage;
    }

    public void setFinanceStage(String financeStage) {
        this.financeStage = financeStage;
    }

    public Integer getInterviewRemarkNum() {
        return interviewRemarkNum;
    }

    public void setInterviewRemarkNum(Integer interviewRemarkNum) {
        this.interviewRemarkNum = interviewRemarkNum;
    }

    public Integer getPositionNum() {
        return positionNum;
    }

    public void setPositionNum(Integer positionNum) {
        this.positionNum = positionNum;
    }

    public Integer getProcessRate() {
        return processRate;
    }

    public void setProcessRate(Integer processRate) {
        this.processRate = processRate;
    }

    public Integer getApprove() {
        return approve;
    }

    public void setApprove(Integer approve) {
        this.approve = approve;
    }

    public Integer getCountryScore() {
        return countryScore;
    }

    public void setCountryScore(Integer countryScore) {
        this.countryScore = countryScore;
    }

    public Integer getCityScore() {
        return cityScore;
    }

    public void setCityScore(Integer cityScore) {
        this.cityScore = cityScore;
    }
}
