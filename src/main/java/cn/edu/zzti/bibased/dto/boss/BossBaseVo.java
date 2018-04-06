package cn.edu.zzti.bibased.dto.boss;

import java.io.Serializable;
import java.util.List;

public class BossBaseVo implements Serializable {
    private String code;
    private String name;
    List<BossBaseVo> subLevelModelList;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BossBaseVo> getSubLevelModelList() {
        return subLevelModelList;
    }

    public void setSubLevelModelList(List<BossBaseVo> subLevelModelList) {
        this.subLevelModelList = subLevelModelList;
    }
}
