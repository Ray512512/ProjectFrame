package com.ray.library.rxjava;

import android.content.Context;

import com.ray.library.R;
import com.ray.library.base.mvp.BaseIView;
import com.ray.library.retrofit.ServerException;
import com.ray.library.utils.L;
import com.ray.library.utils.NetUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by Jam on 16-7-21
 * Description: 自定义Subscribe
 */
public abstract class RxSubscribe<T> implements Observer<T> {
    private Context mContext;
    private BaseIView mBaseIView;


    private RxSubscribe(BaseIView baseIView) {
        this.mBaseIView=baseIView;
        if(mBaseIView!=null)mBaseIView.showLoading();
    }

    /**
     * @param context context
     */
    public RxSubscribe(Context context, BaseIView baseIView) {
        this(baseIView);
        this.mContext = context;
    }




    @Override
    public void onComplete() {
        if(mBaseIView!=null)
        mBaseIView.showDataView();
    }

    @Override
    public void onSubscribe(Disposable s) {
        if(mBaseIView!=null)
        mBaseIView.addRxDestroy(s);
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        L.v("onError",e.toString());
        if(mBaseIView!=null)
        mBaseIView.showDataView();
        e.printStackTrace();
        if(mContext==null){
            _onError("");
            return;
        }
        if (!NetUtils.checkNetWork(mContext)) { //这里自行替换判断网络的代码
            if(mBaseIView!=null)
                mBaseIView.showNetWorkErrorView();
            _onError(mContext.getString(R.string.net_error));
        } else if (e instanceof ServerException) {
            String s=e.getMessage();
            if(e.getMessage().equals("解密失败")){
                L.v("onError","解密失败，尝试重新获取token");
//                MiPaiLifelication.updateToken();
                s=mContext.getString(R.string.token_error);
            }
            _onError(s);
        } else {
            _onError(mContext.getString(R.string.net_failed));
        }
    }

    abstract public  void _onNext(T t);

    abstract public  void _onError(String message);

}
