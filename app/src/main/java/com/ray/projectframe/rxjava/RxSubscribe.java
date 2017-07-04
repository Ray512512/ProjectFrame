package com.ray.projectframe.rxjava;

import android.content.Context;

import com.ray.projectframe.R;
import com.ray.projectframe.base.mvp.BaseIView;
import com.ray.projectframe.retrofit.ServerException;
import com.ray.projectframe.utils.NetUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by Jam on 16-7-21
 * Description: 自定义Subscribe
 */
public abstract class RxSubscribe<T> implements Observer<T> {
    private Context mContext;
    private BaseIView mBaseIView;


    public RxSubscribe(BaseIView baseIView) {
        this.mBaseIView=baseIView;
        mBaseIView.showLoading();
    }

    /**
     * @param context context
     */
    public RxSubscribe(Context context,BaseIView baseIView) {
        this(baseIView);
        this.mContext = context;
    }


    @Override
    public void onComplete() {
        mBaseIView.showDataView();
    }

    @Override
    public void onSubscribe(Disposable s) {
        mBaseIView.addRxDestroy(s);
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        mBaseIView.showDataView();
        e.printStackTrace();
        if (!NetUtils.checkNetWork(mContext)) { //这里自行替换判断网络的代码
            _onError(mContext.getString(R.string.net_error));
        } else if (e instanceof ServerException) {
            _onError(e.getMessage());
        } else {
            _onError(mContext.getString(R.string.net_failed));
        }
    }

    abstract public  void _onNext(T t);

    abstract public  void _onError(String message);

}
