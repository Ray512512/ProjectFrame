package com.ray.library.rxjava.util;

import android.view.View;
import android.widget.EditText;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.ray.library.utils.L;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * 封装一些RxJava常用操作
 * Created by Ray on 2018/3/7.
 */

public class RxManager {
    private static final String TAG = "RxManager";

    /**
     * 无条件轮询 无限循环
     * @param period 间隔时间
     * @param callback 回调action
     */
    public static void interval(int period,RxInterface.intervalInterface1 callback){
        System.out.print("123");
        Observable.interval(period, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> {
            L.v(TAG,"interval 1\t"+aLong);
            callback.action(aLong);
        });
    }

    /**
     * 有条件轮询 满足条件终止
     * @param period 间隔时间
     * @param callback 停止条件  回调action
     */
    public static void interval(long period,RxInterface.intervalInterface2 callback){
        Observable.interval(period, TimeUnit.SECONDS).takeUntil((Predicate<Long>) aLong -> callback.isStop()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> {
            L.v(TAG,"interval 2\t"+aLong);
            callback.action(aLong);
        });
    }


    /**
     * 联合判断，当满足所有条件时决定行为
     * @param callback
     * @param observables
     */
    /*@SafeVarargs
    public static void submit(RxInterface.intervalCombineLatest callback, Observable ... observables){
        Observable.combineLatest(observables, new Function<Object[], Boolean>() {
            @Override
            public Boolean apply(Object[] objects) throws Exception {
                return callback.getResult();
            }
        }).subscribe(new Consumer() {
            @Override
            public void accept(Object b) throws Exception {
                callback.action(b);
            }
        });
    }*/

    /**
     * 防抖功能
     * @param view
     * @param time 防抖间隔
     * @param simple
     */
    public static void clicks(View view,int time,RxInterface.simple simple){
        RxView.clicks(view).throttleFirst(time, TimeUnit.SECONDS).subscribe(o -> simple.action());
    }

    /**
     * 智能搜索优化
     * 防止短时间内连续搜索
     * @param view
     * @param time
     * @param simple
     */
    public static void autoSearch(EditText view, int time, RxInterface.simple simple){
        RxTextView.textChanges(view).debounce(time,TimeUnit.SECONDS).skip(1)//跳过初始值空字符
                .observeOn(AndroidSchedulers.mainThread()).subscribe(charSequence -> simple.action());
    }

}
