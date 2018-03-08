package com.ray.library.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.ray.library.base.ui.BottomTabBaseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by xiaoyu_pc on 2016/3/27.
 */
public class AppManager {
    private static AppManager mInstance;
    private static List<Activity> mActivityStack;
    private Context context;

    private AppManager(Context context) {
        this.context = context.getApplicationContext();
    }

    public static synchronized AppManager getInstance(Context context) {//单例
        if (mInstance == null) {
            mInstance = new AppManager(context);
        }
        return mInstance;
    }


    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (mActivityStack == null) {
            mActivityStack = new ArrayList<>();
        }
        mActivityStack.add(activity);
    }

    /**
     * 获取栈顶Activity（堆栈中最后一个压入的）
     */
    public Activity getTopActivity() {
        Activity activity=null;
        if (mActivityStack != null && mActivityStack.size() > 0) {
            activity = mActivityStack.get(mActivityStack.size() - 1);
        }
        return activity;
    }

    /**
     * 获取栈顶-1Activity（堆栈中最后一个压入的）
     */
    public Activity getTopSecondActivity() {
        Activity activity = null;
        if (mActivityStack != null && mActivityStack.size() > 0) {
            if (mActivityStack.size() - 2 >= 0) {
                activity = mActivityStack.get(mActivityStack.size() - 2);
            }
        }
        return activity;
    }

    /**
     * 结束栈顶Activity（堆栈中最后一个压入的）
     */
    public void killTopActivity() {
        Activity activity = getTopActivity();
        killActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void killActivity(Activity activity) {
        if (activity != null) {
            mActivityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void killActivity(Class<?> cls) {
        for (Activity activity : mActivityStack) {
            if (activity.getClass().equals(cls)) {
                killActivity(activity);
            }
        }
    }

    public boolean hasMantivity() {
        for (Activity activity : mActivityStack) {
            if (activity instanceof BottomTabBaseActivity) {
                return true;
            }
        }
        return false;
    }

    /**
     * 结束所有Activity
     */
    public void killAllActivity() {
        for (int i = 0, size = mActivityStack.size(); i < size; i++) {
            if (mActivityStack.get(i) != null) {
                mActivityStack.get(i).finish();
            }
        }
        mActivityStack.clear();
    }

    /**
     * 退出应用程序
     */
    @SuppressWarnings("deprecation")
    public void AppExit(Context context) {
        try {
            killAllActivity();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
//            RxBusEventManager.getInstance(context).clearAllRxEvent();
//            RxBus.getInstance().removeAllStickyEvents();
            System.exit(0);
        } catch (Exception e) {
        }
    }

    /**
     * 除了主页都关闭
     **/
    public void finishActivityExceptMain() {
        List<Activity> activityList = new ArrayList<>();
        for (int i = 0; i < mActivityStack.size(); i++) {
            Activity activity = mActivityStack.get(i);
            if (!(activity instanceof BottomTabBaseActivity)) {
                activityList.add(activity);
                activity.finish();
            }
        }
        mActivityStack.removeAll(activityList);
    }

   /* *//**
     * 除了登录页都关闭
     **//*
    public void finishActivityExceptLogin() {
        List<Activity> activityList = new ArrayList<>();
        for (Activity activity : mActivityStack) {
            if (!activity.getClass().equals(LoginActivity.class)) {
                activityList.add(activity);
                activity.finish();
            }
        }
        mActivityStack.removeAll(activityList);
    }*/

    /**
     * mainactivity是否存在
     **/
    public boolean isMainActivityIn() {
        if (mActivityStack != null && mActivityStack.size() > 0) {
            for (Activity activity : mActivityStack) {
                if (activity instanceof BottomTabBaseActivity) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }

    }

    /**
     * 顶栈是否是mainactivity
     **/
    public boolean isMainActivity() {
        return getTopActivity() instanceof BottomTabBaseActivity;
    }

    /**
     *获取 mainactivity
     **/
    public BottomTabBaseActivity getMainActivity() {
        if(!isMainActivityIn()){
            Intent intent=new Intent(context,BottomTabBaseActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        if (mActivityStack != null && mActivityStack.size() > 0) {
            for (Activity activity : mActivityStack) {
                if (activity instanceof BottomTabBaseActivity) {
                    return (BottomTabBaseActivity) activity;
                }
            }
            return null;
        } else {
            return null;
        }

    }
    /**
     * 是否在前台运行
     **/
    public boolean isRunningForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        String currentPackageName = cn.getPackageName();
        if (!TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(context.getPackageName())) {
            return true;
        }
        return false;
    }

    public static void start(Context context, Class activity){
        if(context instanceof Activity)
        context.startActivity(new Intent(context,activity));
        else {
            mInstance.getTopActivity().startActivity(new Intent(context,activity));
        }
    }
}
