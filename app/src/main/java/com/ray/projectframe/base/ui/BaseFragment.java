package com.ray.projectframe.base.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ray.projectframe.R;
import com.ray.projectframe.base.mvp.BaseIView;
import com.ray.projectframe.base.mvp.BasePresenter;
import com.ray.projectframe.common.ConstantField;
import com.ray.projectframe.ui.viewhelper.VaryViewHelper;
import com.ray.projectframe.utils.SystemUtil;

import butterknife.ButterKnife;

/**
 * Created by xy on 16/5/11.
 */
public abstract class BaseFragment<P extends BasePresenter>  extends Fragment implements VaryViewHelper.NetWorkErrorListener, VaryViewHelper.TransferListener ,BaseIView {
    protected Context mContext;

    public P mPresenter;
    protected VaryViewHelper mVaryViewHelper;
    protected int viewType = ConstantField.TYPE_VIEW_DATA;

    protected View rootView;// 根视图
    /**
     * 需要初始化Presenter重写
     **/
    protected abstract int inflateContentView();
    protected abstract void initView(Bundle savedInstanceSate);
    protected abstract void initPresenter();

    //用来保存当前界面的状态和恢复上一个界面的状态
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setUserVisibleHint(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (inflateContentView() > 0) {
            rootView = inflater.inflate(inflateContentView(), null);
            rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ButterKnife.bind(this, rootView);
            mContext = getActivity();
            initPresenter();
            initView(savedInstanceState);
            return rootView;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    //设置覆盖加载页面 网络错误页面
    protected void initHelperView(View bindView) {
        mVaryViewHelper = new VaryViewHelper.Builder()
                .setDataView(bindView)//放数据的父布局，逻辑处理在该Activity中处理
                .setLoadingView(LayoutInflater.from(getActivity()).inflate(R.layout.view_loading, null))//加载页，无实际逻辑处理
                .setEmptyView(LayoutInflater.from(getActivity()).inflate(R.layout.view_empty, null))//空页面，无实际逻辑处理
                .setErrorView(LayoutInflater.from(getActivity()).inflate(R.layout.view_error, null))//错误页面
                .setNetWorkErrorView(LayoutInflater.from(getActivity()).inflate(R.layout.view_neterror, null))//网络错误页
//                .setTransferMineView(LayoutInflater.from(getActivity()).inflate(R.layout.view_transfer_mine, null))//我的中转页
                .setRefreshListener(this)//错误页点击刷新实现
                .setTransferListener(this)//点击跳转登录页实现
                .build();
    }

    //展示空页面
    public void showEmpty() {
        if (mVaryViewHelper != null) {
            viewType = ConstantField.TYPE_VIEW_EMPTY;
            mVaryViewHelper.showEmptyView();
        }
    }

    //展示加载页
    public void showLoadingView() {
        if (mVaryViewHelper != null) {
            mVaryViewHelper.showLoadingView();
        }
    }

    //展示网络错误页
    public void showNetWorkErrorView() {
        if (mVaryViewHelper != null) {
            mVaryViewHelper.showNetWorkErrorView();
        }
    }

    //展示数据页
    public void showDataView() {
        if (mVaryViewHelper != null) {
            viewType = ConstantField.TYPE_VIEW_DATA;
            mVaryViewHelper.showDataView();
        }
    }

    //展示错误页
    public void showErrorView() {
        if (mVaryViewHelper != null) {
            viewType = ConstantField.TYPE_VIEW_ERROR;
            mVaryViewHelper.showNetWorkErrorView();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    //跳转去登录
    @Override
    public void onLoginAction() {
    }


    //打开设置网络
    @Override
    public void onSettingNetWork() {
        SystemUtil.openWifi(mContext);
    }

    //刷新覆盖
    @Override
    public void onRefresh() {

    }
}
