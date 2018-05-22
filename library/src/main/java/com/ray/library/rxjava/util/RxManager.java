package com.ray.library.rxjava.util;

import android.view.View;
import android.widget.EditText;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.ray.library.utils.L;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * 封装一些RxJava常用操作
 * Created by Ray on 2018/3/7.
 */

public class RxManager {
    private static final String TAG = "RxManager";


    private static Scheduler[] getScheduler(Scheduler...schedulers){
        Scheduler[] s=new Scheduler[2];
        switch (schedulers.length) {
            case 0:
                s[0]=Schedulers.io();
                s[1]=AndroidSchedulers.mainThread();
                break;
            case 1:
                s[0]=Schedulers.io();
                s[1]=schedulers[0];
                break;
            case 2:
                s[0]=schedulers[0];
                s[1]=schedulers[1];
                break;
            default:
                s[0]=Schedulers.io();
                s[1]=AndroidSchedulers.mainThread();
                break;
        }
        return s;
    }
    /**
     * 无条件轮询 无限循环
     * @param period 间隔时间
     * @param callback 回调action
     * @param schedulers 依次传递订阅线程 执行线程
     */
    public static Disposable interval(int period, RxInterface.intervalInterface1 callback, Scheduler...schedulers){
        Scheduler[] s=getScheduler(schedulers);
        return Observable.interval(period, TimeUnit.SECONDS).subscribeOn(s[0]).observeOn(s[1]).subscribe(aLong -> {
            L.v(TAG,"interval 1\t"+aLong);
            callback.action(aLong);
        });
    }

    /**
     * 有条件轮询 满足条件终止
     * @param period 间隔时间
     * @param callback 停止条件  回调action
     * @param schedulers 依次传递订阅线程 执行线程
     */
    public static Disposable interval(long period,RxInterface.intervalInterface2 callback, Scheduler...schedulers){
        Scheduler[] s=getScheduler(schedulers);
        return Observable.interval(period, TimeUnit.SECONDS).takeUntil((Predicate<Long>) aLong -> callback.isStop()).subscribeOn(s[0]).observeOn(s[1]).subscribe(aLong -> {
            L.v(TAG,"interval 2\t"+aLong);
            callback.action(aLong);
        });
    }

    /**
     * 延时任务
     * @param delayed 延迟时间
     * @param callback  回调action
     * @param schedulers 依次传递订阅线程 执行线程
     */
    public static Disposable delay(long delayed,RxInterface.delayed callback, Scheduler...schedulers){
        Scheduler[] s=getScheduler(schedulers);
       return Observable.timer(delayed,TimeUnit.SECONDS).subscribeOn(s[0]).observeOn(s[1]).subscribe(aLong -> callback.action());
    }

    /**
     * 联合判断，当满足所有条件时决定行为
     * @param callback
     * @param observables
     */
    @SafeVarargs
    public static <T> Disposable combineLatest(RxInterface.combineLatest callback, Observable<T> ... observables){
       return Observable.combineLatest(observables, objects -> callback.getResult()).subscribe(callback::action);
    }

    /**
     * 防抖功能
     * @param view
     * @param time 防抖间隔
     * @param simple
     */
    public static Disposable clicks(View view,int time,RxInterface.simple simple){
       return RxView.clicks(view).throttleFirst(time, TimeUnit.SECONDS).subscribe(o -> simple.action());
    }

    /**
     * 智能搜索优化
     * 防止短时间内连续搜索
     * @param view
     * @param time
     * @param simple
     */
    public static Disposable autoSearch(EditText view, int time, RxInterface.simple simple){
       return RxTextView.textChanges(view).debounce(time,TimeUnit.SECONDS).skip(1)//跳过初始值空字符
                .observeOn(AndroidSchedulers.mainThread()).subscribe(charSequence -> simple.action());
    }

}
