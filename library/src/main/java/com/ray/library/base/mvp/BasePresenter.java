package com.ray.library.base.mvp;

import android.app.Activity;
import android.widget.Toast;


/**
 * Created by ray on 17/5/11.
 */
public abstract class BasePresenter<I extends BaseIView> {
    private static final String TAG = "BasePresenter";
    protected I mView;//view接口
    protected Activity mContext;

    public BasePresenter(Activity mContext, I mView) {
        this.mContext = mContext;
        this.mView = mView;
    }


    protected void showToast(String msg){
        Toast.makeText(mContext,msg, Toast.LENGTH_SHORT).show();
    }

    public void onDetachView() { //销毁的时候
        if (mView != null) {
            mView = null;
        }
    }
}
