package cn.edu.zzti.bibased.dto.lagou;

import java.util.List;

public class PositionDetailResultJsonVo {
     private int totalCount;
     private int resultSize;
     private List<PositionDetailVo> result;

     public int getTotalCount() {
          return totalCount;
     }

     public void setTotalCount(int totalCount) {
          this.totalCount = totalCount;
     }

     public int getResultSize() {
          return resultSize;
     }

     public void setResultSize(int resultSize) {
          this.resultSize = resultSize;
     }

     public List<PositionDetailVo> getResult() {
          return result;
     }

     public void setResult(List<PositionDetailVo> result) {
          this.result = result;
     }
}
