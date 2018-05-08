package cn.edu.zzti.bibased.dto.query;

import cn.edu.zzti.bibased.dto.PositionDetail;

import java.io.Serializable;
import java.util.List;

public class PositionDetailQuery  extends PositionDetail implements Serializable{
    private List<String> cityList;
    private int pageRow;
    private int pageSize;
    private int totalPageRow;

    public int getPageRow() {
        return pageRow;
    }

    public void setPageRow(int pageRow) {
        this.pageRow = pageRow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPageRow() {
        return totalPageRow;
    }

    public void setTotalPageRow(int totalPageRow) {
        this.totalPageRow = totalPageRow;
    }

    public List<String> getCityList() {
        return cityList;
    }

    public void setCityList(List<String> cityList) {
        this.cityList = cityList;
    }
}
