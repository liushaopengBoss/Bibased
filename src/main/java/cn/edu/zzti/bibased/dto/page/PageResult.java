package cn.edu.zzti.bibased.dto.page;

import cn.edu.zzti.bibased.dto.PositionDetail;

import java.io.Serializable;
import java.util.List;

public class PageResult implements Serializable {

    private int pageSize;
    private int pageNum;
    private List<PositionDetail> positionDetailList;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<PositionDetail> getPositionDetailList() {
        return positionDetailList;
    }

    public void setPositionDetailList(List<PositionDetail> positionDetailList) {
        this.positionDetailList = positionDetailList;
    }
}
