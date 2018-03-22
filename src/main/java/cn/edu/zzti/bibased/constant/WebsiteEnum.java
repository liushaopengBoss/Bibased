package cn.edu.zzti.bibased.constant;

/**
 * 信息采集的网站
 *
 * Created by huaidou on  2018/1/11
 */
public enum WebsiteEnum {
    LAGOU("lagou","拉钩"),ZHILIAN("zhilian","智联"),
    JOB("51job","51Job");

    private String webCode;
    private String webName;

    WebsiteEnum(String webCode, String webName) {
        this.webCode = webCode;
        this.webName = webName;
    }

    public String getWebCode() {
        return webCode;
    }

    public String getWebName() {
        return webName;
    }
}
