package com.ray.library.utils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ray on 2017/5/5.
 * email：1452011874@qq.com
 */

public class StringUtil {

    /**
     * 判断String是否为空
     **/
    public static boolean isEmpty(String string) {
        return string == null || string.length() == 0 || string.equals("null");
    }

    public static String listToString(List list, String separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return sb.toString().substring(0,sb.toString().length()-1);}

    public static ArrayList<String > arrayToList(String[] array){
        ArrayList<String > list=new ArrayList<>();
        if(array==null){
            return list;
        }
        for (String s:array){
            if(!TextUtils.isEmpty(s))
                list.add(s);
        }
        return list;
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}
