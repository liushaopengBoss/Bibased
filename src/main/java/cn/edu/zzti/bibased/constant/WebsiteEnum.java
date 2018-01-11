package cn.edu.zzti.bibased.constant;

/**
 * 用途
 * <p>
 * Created by huaidou on  2018/1/11
 */
public enum WebsiteEnum {
    LAGOU(1,"拉钩");

    private int webId;
    private String webName;

    WebsiteEnum(int webId, String webName) {
        this.webId = webId;
        this.webName = webName;
    }

    public int getWebId() {
        return webId;
    }

    public void setWebId(int webId) {
        this.webId = webId;
    }

    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }
}
