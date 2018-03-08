package com.ray.library.rxjava.util;

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
}
