package com.ray.projectframe.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.ray.projectframe.R;


/**
 * Created by ray on 2016/3/31.
 */
public class AlertDialogUtil {

    private static AlertDialog.Builder getBuild(Context context,String message){
        return new AlertDialog.Builder(context)
                .setTitle(context.getResources().getString(R.string.alertdialog_title))
                .setMessage(message);
    }
    /**
     * 自定义两个按钮，自定义提示内容 监听positive按钮
     * */
    public static  void  AlertDialog(Context context, String message, String PositiveButton, String NegativeButton, DialogInterface.OnClickListener listener){
         getBuild(context,message)
        .setPositiveButton(PositiveButton, listener)
        .setNegativeButton(NegativeButton, (dialogInterface, i) -> dialogInterface.dismiss())
        .create().show();
    }

    /**
     * 自定义两个按钮，自定义提示内容 监听两个按钮
     * */
    public static  void  AlertDialog(Context context, String message, String PositiveButton, String NegativeButton, DialogInterface.OnClickListener listener, DialogInterface.OnClickListener listener2){
         getBuild(context,message)
        .setPositiveButton(PositiveButton, listener)
        .setNegativeButton(NegativeButton, listener2)
        .create().show();
    }
    /**
     * 自定义两个按钮，自定义所有， 监听positive按钮
     * */
    public static  void  AlertDialog(Context context, String title, String message, String PositiveButton, String NegativeButton, DialogInterface.OnClickListener listener){
        getBuild(context,message)
        .setTitle(title)
        .setPositiveButton(PositiveButton, listener)
        .setNegativeButton(NegativeButton, (dialogInterface, i) -> dialogInterface.dismiss())
        .create().show();
    }

    /**
     *
     *自定义一个按钮 无任何监听 点击取消
     **/
    public static  void  AlertDialog(Context context, String message, String NegativeButton){
        getBuild(context,message)
        .setNegativeButton(NegativeButton, (dialogInterface, i) -> dialogInterface.dismiss())
        .create().show();
    }

    public static  void  AlertDialog(Context context, String PositiveButton, String message,DialogInterface.OnClickListener listener){
        getBuild(context,message)
        .setPositiveButton(PositiveButton, listener)
        .create().show();
    }
}
