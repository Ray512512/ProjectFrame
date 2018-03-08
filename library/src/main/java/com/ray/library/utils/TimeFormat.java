package com.ray.library.utils;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.Locale;

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

    public  static String getDateTimeNumber(long time){
        SimpleDateFormat newtime= new SimpleDateFormat("yyyyMMdd");
        return  newtime.format(time);
    }

    /**
     *
     * @param time 20180210
     * @return
     */
    public   static boolean isTimeOut(String time){
        boolean isTimeout=false;
        try{
            int now= Integer.parseInt(getDateTimeNumber(System.currentTimeMillis()))/*+new Random().nextInt(5)*/;
            int orderTime= Integer.parseInt(time);
            isTimeout=now>orderTime;
            L.v("isTimeOut","当前："+now+"\t"+"购物车时间："+orderTime);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  isTimeout;
    }

    /**
     * data 20180212 格式
     *   根据日期取得星期几
     */
    public static String getWeek(String data){
        String week="";
        if(TextUtils.isEmpty(data))return week;
        Date date=new Date();
        try {
            SimpleDateFormat newtime= new SimpleDateFormat("yyyyMMdd");
            date=newtime.parse(data);
        }catch (Exception e){
            e.printStackTrace();
            L.v("getWeek",e.toString());
        }
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        week = sdf.format(date);
        L.v("getWeek",week);
        return week;
    }
    public static long getSubTime(String time1, String time2){
        long t1=getStringToLong(time1);
        long t2=getStringToLong(time2);
        long sub=t1-t2;
        return Math.abs(sub);
    }
    public static String getTomorrow(){
        Date date=new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,-1);//把日期往前减少一天，若想把日期向后推一天则将负数改为正数
        date=calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(date);
        return dateString;
    }


    /**
     * 把毫秒转换成：1：20：30这样的形式
     * @param timeMs
     * @return
     */

    public static String stringForTime(int timeMs){
        StringBuilder mFormatBuilder = new StringBuilder();
        Formatter mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
        int totalSeconds = timeMs/1000;
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds/60)%60;
        int hours = totalSeconds/3600;
        mFormatBuilder.setLength(0);
        if(hours>0){
            return mFormatter.format("%d:%02d:%02d",hours,minutes,seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d",minutes,seconds).toString();
        }
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
