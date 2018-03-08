package com.ray.library.rxjava;


import com.ray.library.bean.BaseModel;
import com.ray.library.retrofit.ServerException;
import com.ray.library.utils.L;

import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Jam on 16-6-12
 * Description: Rx 一些巧妙的处理
 */
public class RxHelper {
    /**
     * 对结果进行预处理
     *
     * @param <T>
     * @return
     */
    private static final int MAX_RETRY_TIME = 3;
    public static <T> ObservableTransformer<BaseModel<T>, T> handleResult() {
        return upstream -> upstream.flatMap(result -> {
            if (result.success()) {
                return createData(result.data);
            } else {
                return Observable.error(new ServerException(result.msg));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    }

    public static Function<Observable<Throwable>, ObservableSource<?>> retryWhen() {
        final int[] currentTime = {0};
        return throwableObservable -> throwableObservable.flatMap((Function<Throwable, ObservableSource<?>>) throwable -> {
            if(throwable instanceof SocketTimeoutException){ //目前只处理超时情况
                if(currentTime[0]++<MAX_RETRY_TIME) {
                    L.v("","超时重试"+ currentTime[0] +"/"+MAX_RETRY_TIME);
                    return Observable.just(1).delay(1, TimeUnit.SECONDS);
                }
                else return Observable.error(new Throwable("重试已达到最大尝试次数"));
            }else {
                return Observable.error(throwable);
            }
        });
    }

        /**
         * 创建成功的数据
         *
         * @param data
         * @param <T>
         * @return
         */
    private static <T> Observable<T> createData(final T data) {

        return Observable.create(subscriber -> {
            try {
                subscriber.onNext(data);
                subscriber.onComplete();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }
}
