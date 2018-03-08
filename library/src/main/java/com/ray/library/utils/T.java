package com.ray.library.utils;

import android.content.Context;
import android.widget.Toast;

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
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短暂显示Toast提示(来自res) *
     */
    public static void show(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    public static void show(String text) {
        Toast.makeText(BaseApplication.getInstance(), text, Toast.LENGTH_SHORT).show();
    }

    public static void show(int resId) {
        Toast.makeText(BaseApplication.getInstance(), resId, Toast.LENGTH_SHORT).show();
    }
}
