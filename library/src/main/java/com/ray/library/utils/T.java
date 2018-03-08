package com.ray.library.utils;

import android.content.Context;

import com.ray.library.BaseApplication;


/**
 * Created by Ray on 2017/5/16.
 * email：1452011874@qq.com
 */

public class T {

    /**
     * 短暂显示Toast提示(来自String) *
     */
    public static void show(Context context, String text) {
        android.widget.Toast.makeText(context, text, android.widget.Toast.LENGTH_SHORT).show();
    }

    /**
     * 短暂显示Toast提示(来自res) *
     */
    public static void show(Context context, int resId) {
        android.widget.Toast.makeText(context, resId, android.widget.Toast.LENGTH_SHORT).show();
    }

    public static void show(String text) {
        android.widget.Toast.makeText(BaseApplication.getInstance(), text, android.widget.Toast.LENGTH_SHORT).show();
    }

    public static void show(int resId) {
        android.widget.Toast.makeText(BaseApplication.getInstance(), resId, android.widget.Toast.LENGTH_SHORT).show();
    }
}
