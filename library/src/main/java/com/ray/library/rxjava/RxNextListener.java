package com.ray.library.rxjava;

/**
 * Created by Ray on 2017/5/11.
 * email：1452011874@qq.com
 */

public interface RxNextListener<T> {
    void onNext(T t);
    void onError(String e);
}
