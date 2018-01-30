package cn.edu.zzti.bibased.dto.lagou;

import java.util.List;

/**
 * 用于json的转换
 * <p>
 * Created by huaidou on  2018/1/30
 */
public class CompanyResultJsonVO {

    private int pageSize;

    private int start;
    private int totalCount;
    private int pageNo;

    private List<CompanyVO> result;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public List<CompanyVO> getResult() {
        return result;
    }

    public void setResult(List<CompanyVO> result) {
        this.result = result;
    }
}
