package frame.project.ray.projectframe.rxjava;

import android.content.Context;

import java.io.Serializable;

import frame.project.ray.projectframe.retrofit.CacheManager;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
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
    public static <T> Flowable<T> load(final Context context, final String cacheKey, final long expireTime, Flowable<T> fromNetwork, boolean forceRefresh) {
        Flowable<T> fromCache = Flowable.create((FlowableOnSubscribe<T>) e -> {
            T cache = (T) CacheManager.readObject(context, cacheKey, expireTime);
            if (cache != null) {
                e.onNext(cache);
            } else {
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
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
            return Flowable.concat(fromCache, fromNetwork);
        }

    }


}
