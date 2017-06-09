package frame.project.ray.projectframe.utils;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ray on 2015/7/9.
 */
public class TimeFormat {
    public static final long HOUR_12=12L * 60L * 60L * 1000L;

    /**
     * 长整型时间转指定格式字符串
     * */
	public   static String getDateTime(long time){
        SimpleDateFormat newtime= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
            return  newtime.format(time);
}

    public   static String getDateTime2(long time){
        SimpleDateFormat newtime= new SimpleDateFormat("yyyy-MM-dd");

        return  newtime.format(time);
    }
    /**
     * 指定字符串转长整型
     * */
    public static long getStringToLong(String time) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try{
            date = sdf.parse(time);
            } catch(ParseException e) {
            e.printStackTrace();
            }
        L.v("getStringToLong", date.getTime() + "");
        return date.getTime();
        }
    /**
     * 指定字符串转时间格式
     * */
    public static Date getStringToDate(String time) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try{
            date = sdf.parse(time);
        } catch(ParseException e) {
            e.printStackTrace();
        }
        L.v("getStringToDate", date + "");
        return date;
    }

    /**
     * 指定格式yyyy-MM-dd HH:mm:ss 转 X时X分
     * */
    public static String getHourAndSecond(Long time){
        if(time==0){
            return "";
        }
        String timeStr=getDateTime(time);
        if(TextUtils.isEmpty(timeStr)){
            return "";
        }
        String result="";
        String[]s=timeStr.split(" ");
        if(s.length==2){
            if(s[1].indexOf("0")==0){
                result=s[1].substring(1,5);
            }else
                result=s[1].substring(0,5);
        }
        L.v("getHourAndSecond", result + time);
        return result;
    }
    /**
     * 指定格式yyyy-MM-dd HH:mm:ss 转 X月X日
     * */
    public static String getMonthAndDay(String time){
        if(TextUtils.isEmpty(time)){
            return "";
        }
        String result="";
        String[]ss;
        String[]s=time.split(" ");
        if(s.length>0){
        ss=s[0].split("-");
        if(ss.length>2){
        result=ss[1]+"月"+ss[2]+"日";
         }
        }
        L.v("getMonthAndDay", result + time);
        return result;
    }

    /**
     * 指定格式yyyy-MM-dd HH:mm:ss 转 X年X月X日
     * */
    public static String getYearMonthAndDay(String time){
        if(TextUtils.isEmpty(time)){
            return "";
        }
        String result="";
        String[]ss;
        String[]s=time.split(" ");
        if(s.length>0){
            ss=s[0].split("-");
            if(ss.length>2){
                result=ss[0]+"年"+ss[1]+"月"+ss[2]+"日";
            }
        }
        return result;
    }
    public static String getYearMonthAndDay(long  time){
        String time2=getDateTime(time);
        if(TextUtils.isEmpty(time2)){
            return "";
        }
        String result="";
        String[]ss;
        String[]s=time2.split(" ");
        if(s.length>0){
            ss=s[0].split("-");
            if(ss.length>2){
                result=ss[0]+"年"+ss[1]+"月"+ss[2]+"日";
            }
        }
        return result;
    }

    /**
     * 传入时间和当前时间对比是否大于了12小时
     * */
    public static boolean isOver12Hour(long time){
        if(System.currentTimeMillis()-time>HOUR_12){
            return true;
        }
        return false;
    }
}
