package cn.edu.zzti.bibased.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by huaidou on  2018/4/19
 */
public class RequestHolder  {

    private static  final  ThreadLocal<HttpServletRequest> threadLocal = new ThreadLocal<>();

    public static HttpServletRequest getRequest(){
        return threadLocal.get();
    }

    public static void setRequest(HttpServletRequest request){
        threadLocal.set(request);
    }

    public static void clear(){
        threadLocal.remove();
    }

}
