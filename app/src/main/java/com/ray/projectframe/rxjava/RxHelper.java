package com.ray.projectframe.rxjava;


import com.ray.projectframe.retrofit.BaseModel;
import com.ray.projectframe.retrofit.ServerException;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
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

    public static <T> ObservableTransformer<BaseModel<T>, T> handleResult() {
        return upstream -> upstream.flatMap(new Function<BaseModel<T>, Observable<T>>() {
            @Override
            public Observable<T> apply(@NonNull BaseModel<T> result) throws Exception {
                if (result.success()) {
                    return createData(result.data);
                } else {
                    return Observable.error(new ServerException(result.msg));
                }
            }
        }).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread());

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
