package cn.edu.zzti.bibased.constant;

public enum ProjectItem {
    CITY("获取城市信息",1)
    ,POSITION("职位",2),
    COMPANY("获取公司信息",3),
    POSITIONDETAIL("职位详情",4);

    private String name;
    private int code;

    ProjectItem(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static  String getProjectItemCode(int code){
        for(ProjectItem item :ProjectItem.values()){
            if(item.getCode() == code){
                return item.getName();
            }
        }
        return null;
    }
}
