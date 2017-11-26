package cn.edu.zzti.bibased.pojo;

public class PojoTest {
    private String id;
    private String namr;

    public PojoTest(String id, String namr) {
        this.id = id;
        this.namr = namr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamr() {
        return namr;
    }

    public void setNamr(String namr) {
        this.namr = namr;
    }
}
