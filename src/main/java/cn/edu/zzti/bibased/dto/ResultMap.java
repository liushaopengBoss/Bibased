package cn.edu.zzti.bibased.dto;

import java.io.Serializable;

public class ResultMap  implements Serializable{
    private int code;
    private String message;
    private Object data;

    public ResultMap() {
        code = 200;
        data = "success";
    }

    public ResultMap(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
