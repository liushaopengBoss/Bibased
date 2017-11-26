package cn.edu.zzti.bibased.pojo;

public class PojoTest {
    private String id;
    private String name;

    public PojoTest(String id, String namr) {
        this.id = id;
        this.name = namr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
