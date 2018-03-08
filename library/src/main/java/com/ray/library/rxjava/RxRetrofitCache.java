package com.ray.library.rxjava;

import android.content.Context;

import com.ray.library.retrofit.CacheManager;

import java.io.Serializable;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Jam on 16-7-6
 * Description:
 * RxJava + Retrofit 的缓存机制
 */
public class RxRetrofitCache {

    /**
     * @param context
     * @param cacheKey     缓存key
     * @param expireTime   过期时间 0 表示有缓存就读，没有就从网络获取
     * @param fromNetwork  从网络获取的Observable
     * @param forceRefresh 是否强制刷新
     * @param <T>
     * @return
     */
    public static <T> Observable<T> load(final Context context, final String cacheKey, final long expireTime, Observable<T> fromNetwork, boolean forceRefresh) {
        Observable<T> fromCache = Observable.create((ObservableOnSubscribe<T>) e -> {
            T cache = (T) CacheManager.readObject(context, cacheKey, expireTime);
            if (cache != null) {
                e.onNext(cache);
            } else {
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        /**
         * 这里的fromNetwork 不需要指定Schedule,在handleRequest中已经变换了
         */
        fromNetwork = fromNetwork.map(t -> {
            CacheManager.saveObject(context, (Serializable) t, cacheKey);
            return t;
        });
        if (forceRefresh) {
            return fromNetwork;
        } else {
            return Observable.concat(fromCache, fromNetwork);
        }

    }


}
