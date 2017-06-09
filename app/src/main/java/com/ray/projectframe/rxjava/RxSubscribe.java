package com.ray.projectframe.rxjava;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import frame.project.ray.projectframe.R;
import com.ray.projectframe.retrofit.ServerException;
import com.ray.projectframe.utils.NetUtils;


/**
 * Created by Jam on 16-7-21
 * Description: 自定义Subscribe
 */
public abstract class RxSubscribe<T> implements Subscriber<T> {
    private Context mContext;
    private ProgressDialog dialog;
    private String msg = "请稍后...";


    public RxSubscribe() {
    }

    protected boolean showDialog() {
        if (mContext == null || TextUtils.isEmpty(msg)) return false;
        return true;
    }

    /**
     * @param context context
     * @param msg     dialog message
     */
    public RxSubscribe(Context context, String msg) {
        this.mContext = context;
        this.msg = msg;
    }

    public RxSubscribe(Context context, int msg) {
        this.mContext = context;
        this.msg = mContext.getString(msg);
    }


    /**
     * @param context context
     */
    public RxSubscribe(Context context) {
        this(context, "请稍后...");
    }

    @Override
    public void onComplete() {
        if (showDialog())
            dialog.dismiss();
    }

    @Override
    public void onSubscribe(Subscription s) {
        if (showDialog()) {
            dialog = new ProgressDialog(mContext);
            dialog.setMessage(msg);
            //点击取消的时候取消订阅
            dialog.setOnCancelListener(dialog1 -> {
                onCancel();
            });
            dialog.show();
        }
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (!NetUtils.checkNetWork(mContext)) { //这里自行替换判断网络的代码
            _onError(mContext.getString(R.string.net_error));
        } else if (e instanceof ServerException) {
            _onError(e.getMessage());
        } else {
            _onError(mContext.getString(R.string.net_failed));
        }
        if (showDialog())
            dialog.dismiss();
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);

    /**
     * 取消的时候，取消对observable的订阅，同时也取消了http请求
     */
    public void onCancel() {
//        if (!this.isUnsubscribed()) {
//            this.unsubscribe();
    }
}
