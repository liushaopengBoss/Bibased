package cn.edu.zzti.bibased.constant;

public enum ProjectItem {
    CITY("城市",1)
    ,POSITION("职位",2),
    COMPANY("公司",3),
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

    public static  int getProjectItemCode(String name){
        for(ProjectItem item :ProjectItem.values()){
            if(item.getName().equals(name)){
                return item.getCode();
            }
        }
        return -1;
    }
}
