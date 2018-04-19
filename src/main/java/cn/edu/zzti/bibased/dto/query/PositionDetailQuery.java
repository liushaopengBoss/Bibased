package cn.edu.zzti.bibased.dto.query;

import cn.edu.zzti.bibased.dto.PositionDetail;

import java.io.Serializable;
import java.util.List;

public class PositionDetailQuery  extends PositionDetail implements Serializable{
    private List<String> cityList;

    public List<String> getCityList() {
        return cityList;
    }

    public void setCityList(List<String> cityList) {
        this.cityList = cityList;
    }
}
