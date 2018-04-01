package cn.edu.zzti.bibased.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * zhi
 * <p>
 * Created by huaidou on  2018/1/11
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Positions implements Serializable {
    private long id;
    private String positionUrl;//职位url链接地址
    private String positionName;//职位名称
    private String include;//属于哪个网站
    private long parentId;//父id  如果父节点为0 则为根节点
    private long rootId;//根节点
    private boolean leaf = true;//是否为叶子节点
    private String dateVersion;
    private long createTime;//创建时间
    private long opTime;//修改时间
    private long lastVer;// 版本
    private int isValid;//是否有效
    /**
     * 数据分析使用
     */
    private int num;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPositionUrl() {
        return positionUrl;
    }

    public void setPositionUrl(String positionUrl) {
        this.positionUrl = positionUrl;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getInclude() {
        return include;
    }

    public void setInclude(String include) {
        this.include = include;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public long getRootId() {
        return rootId;
    }

    public void setRootId(long rootId) {
        this.rootId = rootId;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
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

    public String getDateVersion() {
        return dateVersion;
    }

    public void setDateVersion(String dateVersion) {
        this.dateVersion = dateVersion;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
