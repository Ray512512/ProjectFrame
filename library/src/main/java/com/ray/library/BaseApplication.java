package com.ray.library;

import android.app.Application;

import com.ray.library.common.CrashHandler;

/**
 * Created by Ray on 2017/5/16.
 * email：1452011874@qq.com
 */

public class BaseApplication extends Application {
    private static BaseApplication instance;


    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);
        instance=this;
    }
}
