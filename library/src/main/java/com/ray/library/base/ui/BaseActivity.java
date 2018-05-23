package com.ray.library.base.ui;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.ray.library.R;
import com.ray.library.base.mvp.BaseIView;
import com.ray.library.base.mvp.BasePresenter;
import com.ray.library.rxbus.RxBus;
import com.ray.library.utils.AppManager;
import com.ray.library.utils.SystemUtil;
import com.ray.library.utils.ViewUtils;
import com.ray.library.view.viewhelper.VaryViewHelper;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * Created by ray on 2017/4/13.
 * ButterKnife.bind(this); 在子类initView中调用
 */

public abstract class BaseActivity<P extends BasePresenter>  extends AppCompatActivity implements VaryViewHelper.NetWorkErrorListener,BaseIView {
    public Context mContext;
    protected VaryViewHelper mVaryViewHelper;
    protected P mPresenter;
    private CompositeDisposable disposables2Destroy;// 管理Destroy取消订阅者者

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        disposables2Destroy=new CompositeDisposable();
        AppManager.getInstance(mContext).addActivity(this);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(inflateContentView());
        RxBus.register(this);

        initPresenter();
        initView(savedInstanceState);
        initEvents();
    }

    protected abstract int inflateContentView();
    /**
     * 初始化p
     **/
    protected abstract void initPresenter() ;

    /**
     * 初始化view
     * */
    protected abstract void initView(Bundle savedInstanceState) ;
    /**
     * 初始化事件
     */
    protected abstract void initEvents();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDetachView();
        if(mVaryViewHelper!=null)mVaryViewHelper.releaseVaryView();
        disposables2Destroy.dispose();
        RxBus.unregister(this);
        AppManager.getInstance(mContext).killActivity(this);
    }

    //展示加载页
    @Override
    public void showLoading() {
        if (mVaryViewHelper != null) {
            mVaryViewHelper.showLoadingView();
        }
    }

    //展示网络错误页
    @Override
    public void showNetWorkErrorView() {
        if (mVaryViewHelper != null) {
            mVaryViewHelper.showNetWorkErrorView();
        }
    }

    //展示服务器错误页
    @Override
    public void showErrorView() {
        if (mVaryViewHelper != null) {
            mVaryViewHelper.showErrorView();
        }
    }

    @Override
    public void showEmptyView() {
        if (mVaryViewHelper != null) {
            mVaryViewHelper.showEmptyView();
        }
    }

    //展示数据页
    @Override
    public void showDataView() {
        if (mVaryViewHelper != null) {
            mVaryViewHelper.showDataView();
        }
    }

    @Override
    public void addRxDestroy(Disposable d) {
        disposables2Destroy.add(d);
    }


    /**
     * 当需要用到异常页面替换时 调用initHelperView
     * 不需要时不调用即可
     * mVaryViewHelper具体实例化可写一个公共方法自己实现
     */
    protected void initHelperView(){
        initHelperView(ViewUtils.getRootView(this));
    }
    //设置覆盖加载页面 网络错误页面
    protected void initHelperView(View bindView) {
        mVaryViewHelper = new VaryViewHelper.Builder()
                .setDataView(bindView)//放数据的父布局，逻辑处理在该Activity中处理
                .setLoadingView(LayoutInflater.from(this).inflate(R.layout.view_loading, null))//加载页，无实际逻辑处理
                .setEmptyView(LayoutInflater.from(this).inflate(R.layout.view_empty, null))//空页面，无实际逻辑处理
                .setErrorView(LayoutInflater.from(this).inflate(R.layout.view_error, null))//错误页面
                .setNetWorkErrorView(LayoutInflater.from(this).inflate(R.layout.view_neterror, null))//网络错误页
                .setRefreshListener(this)//错误页点击刷新实现
                .build();
    }



    //打开设置网络
    @Override
    public void onSettingNetWork() {
        SystemUtil.openWifi(mContext);
    }

    //刷新覆盖
    @Override
    public void reTry() {

    }

    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }
}
