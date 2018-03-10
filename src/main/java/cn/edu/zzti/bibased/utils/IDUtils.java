package cn.edu.zzti.bibased.utils;


import java.util.Calendar;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * ID生成器
 *
 * Created by huaidou on  2018/1/13
 */
public class IDUtils {

    /**
     * 职位类别的Id
     *
     * @return
     */
    public static  long getJobsIntId(){
        return System.nanoTime();
    }
}
