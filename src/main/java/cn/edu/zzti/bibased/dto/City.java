package cn.edu.zzti.bibased.dto;

import java.io.Serializable;

/**
 * 用途
 * <p>
 * Created by huaidou on  2018/1/13
 */
public class City implements Serializable {
    private Integer id;
    private String cityName;
    private String include;
    private long createTime;//创建时间
    private long opTime;//修改时间
    private long lastVer;// 版本
    private int isValid;//是否有效

    private String linkId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getInclude() {
        return include;
    }

    public void setInclude(String include) {
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

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }
}
