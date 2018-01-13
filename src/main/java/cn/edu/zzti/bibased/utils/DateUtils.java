package cn.edu.zzti.bibased.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间工具类
 * <p>
 * Created by huaidou on  2018/1/13
 */
public class DateUtils {
    public static String YYMMDD = "yyyy-MM-dd";
    public static String YYMMDD_HHmm = "yyyy-MM-dd HH:mm";
    public static String YYMMDD_HHmmSS = "yyyy-MM-dd HH:mm:ss";
    public static String HHmmSS = "HH:mm:ss";
    public static String YYMMDDHHmmssSSS = "yyyyMMddHHmmssSSS";
    public static String HHmm = "HH:mm";

    public static String HHMMSS_000000= " 00:00:00";

    /**
     * 获取当前的年份
     *
     * @return
     */
    public static  int  getNowYear(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }
    /**
     * 获取当前的月份
     *
     * @return
     */
    public static int getNowMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH) + 1;
    }
    /**
     * 返回当前int时间类型，market系统在使用，不要修改此方法
     * @return
     */
    public static int currentTime(){
        return Integer.parseInt(System.currentTimeMillis()/1000 + "");
    }

    /**
     * 将数字日期解析成日期对象
     *
     * @param date
     * @return
     */
    public static Date parseInt(int date)  {
        if (date == 0) {
            return null;
        }
        long dt = (long)date * 1000;
        Date d = new Date(dt);
        return  d;
    }

    /**
     *将日期转换为int 型时间
     * @param date
     * @return
     */
    public static int parseInt(Date date)  {
        if (date == null) {
            return 0;
        }
        return Integer.parseInt(date.getTime() / 1000 +"");
    }


    /**
     * 将数字日期解析成日期对象
     *
     * @param date
     * @return
     */
    public static String formatInt(int date)  {
        if (date == 0) {
            return null;
        }
        return formatInt(date,YYMMDD_HHmmSS);

    }

    /**
     * 将数字日期解析成日期对象
     *
     * @param date
     * @param format 取值如：DateUtil.YYMMDD
     * @return
     */
    public static String formatInt(int date, String format)  {
        if (date == 0) {
            return null;
        }
        long dt = (long)date * 1000;
        long dtt =Long.parseLong(dt +"");
        Date d = new Date(dtt);
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            return formater.format(d);
        } catch (Exception e) {
            return null;
        }

    }
    /**
     * 获取星期名称
     *
     * @param date 日期
     * @return
     */
    public static String getDayweek(Date date) {
        String[] weeks = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayinweek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return weeks[dayinweek];
    }

    /**
     * 计算两个日期之间相差的天数
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate,Date bdate) throws ParseException
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }
    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getMonthNowDate() {
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DATE);
        String now = "";
        String mop = "-";
        String dop = "-";
        if (month < 10) {
            mop = mop + "0";
        }
        if (day < 10) {
            dop = dop + "0";
        }

        now = year + mop + month + dop + day;
        return now;
    }


    /**
     * 获得距指定时间多少天之后的日期
     *
     * @return
     */
    public static Date getAfterDate(Date date, int days) {

        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + days);
        return now.getTime();
    }


    /**
     * 获得指定月份的周数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getTotalWeeksOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, 1);
        c.add(Calendar.DATE, -1);// 日期减1
        return c.get(Calendar.WEEK_OF_MONTH);
    }


    /**
     * 获取指定年月的 最后一天
     * @param date yyyyMM
     * @return
     */
    public static String getMonthLastday(String date) {
        Calendar cal = Calendar.getInstance();
        int year = Integer.valueOf(date.substring(0,4));
        int month = Integer.valueOf(date.substring(4,6));
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH,month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(cal.getTime());
    }

    /**
     * 获得指定月份的天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getDaysOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, 1);
        c.add(Calendar.DATE, -1);// 日期减1
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 往后推迟num天
     *
     * @param start
     * @param num
     * @param unit  Calendar.DATE Calendar.MINUTE Calendar.HOUR Calendar.MONTH
     * @return 推迟后的date
     */
    public static Date dateAdd(Date start, int num, int unit) {
        Calendar c = Calendar.getInstance();
        c.setTime(start);
        switch (unit) {
            case Calendar.DATE:
                c.set(Calendar.DATE, c.get(Calendar.DATE) + num);
                break;
            case Calendar.MINUTE:
                c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + num);
                break;
            case Calendar.HOUR:
                c.set(Calendar.HOUR, c.get(Calendar.HOUR) + num);
                break;
            case Calendar.MONTH:
                c.set(Calendar.MONTH, c.get(Calendar.MONTH) + num);
                break;
            default:
                c.set(Calendar.MONTH, c.get(Calendar.MONTH) + num);
                break;
        }
        return c.getTime();
    }


    /**
     * 往前提前num天
     *
     * @param start
     * @param num
     * @return 提前后的date
     */
    public static Date dateSub(Date start, int num) {
        Calendar c = Calendar.getInstance();
        c.setTime(start);
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - num);
        return c.getTime();
    }

}
