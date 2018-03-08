package com.ray.library.base.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ray.library.R;
import com.ray.library.base.mvp.BaseIView;
import com.ray.library.base.mvp.BasePresenter;
import com.ray.library.rxbus.RxBus;
import com.ray.library.utils.SystemUtil;
import com.ray.library.utils.ViewUtils;
import com.ray.library.view.viewhelper.VaryViewHelper;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by ray on 16/5/11.
 * ButterKnife.bind(this,view); 在子类initView中调用
 */
public abstract class BaseFragment<P extends BasePresenter>  extends Fragment implements VaryViewHelper.NetWorkErrorListener,BaseIView {
    protected Context mContext;

    public P mPresenter;
    protected VaryViewHelper mVaryViewHelper;
    private CompositeDisposable disposables2Destroy;// 管理Destroy取消订阅者者

    /**
     * 需要初始化Presenter重写
     **/
    protected abstract int inflateContentView();
    protected abstract void initView(View v);
    protected abstract void initPresenter();
    protected abstract void initEvents();


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView;
        if (inflateContentView() > 0) {
            rootView = inflater.inflate(inflateContentView(), null);
            rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            mContext = getActivity();
            disposables2Destroy=new CompositeDisposable();

            RxBus.register(this);
            initPresenter();
            initView(rootView);
            initEvents();
            return rootView;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void initHelperView(){
        initHelperView(ViewUtils.getRootView(getActivity()));
    }
    //设置覆盖加载页面 网络错误页面
    protected void initHelperView(View bindView) {
        mVaryViewHelper = new VaryViewHelper.Builder()
                .setDataView(bindView)//放数据的父布局，逻辑处理在该Activity中处理
                .setLoadingView(LayoutInflater.from(getActivity()).inflate(R.layout.view_loading, null))//加载页，无实际逻辑处理
                .setEmptyView(LayoutInflater.from(getActivity()).inflate(R.layout.view_empty, null))//空页面，无实际逻辑处理
                .setErrorView(LayoutInflater.from(getActivity()).inflate(R.layout.view_error, null))//错误页面
                .setNetWorkErrorView(LayoutInflater.from(getActivity()).inflate(R.layout.view_neterror, null))//网络错误页
                .setRefreshListener(this)//错误页点击刷新实现
                .build();
    }
    //展示加载页
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null) mPresenter.onDetachView();
        if(mVaryViewHelper!=null)mVaryViewHelper.releaseVaryView();
        disposables2Destroy.dispose();
        RxBus.unregister(this);
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
    public void onResume() {
        super.onResume();
//        MobclickAgent.onPageStart(getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPageEnd(getClass().getSimpleName());
    }
}
