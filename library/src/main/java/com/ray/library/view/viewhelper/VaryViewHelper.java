package com.ray.library.view.viewhelper;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ray.library.R;


/**
 * 作者： corer   时间： 2015/10/18.
 * 功能：帮助切换错误，数据为空，正在加载的页面
 * 修改：
 */
public class VaryViewHelper {
    /**
     * 切换不同视图的帮助类
     */
    public OverlapViewHelper mViewHelper;
    /**
     * 错误页面
     */
    public View mErrorView;
    /**
     * 正在加载页面
     */
    public View mLoadingView;
    /**
     * 数据为空的页面
     */
    public View mEmptyView;

    /**
     * 网络错误页
     */
    public View mNetWorkErrorView;


    public VaryViewHelper(View view) {
        this(new OverlapViewHelper(view));
    }

    public VaryViewHelper(OverlapViewHelper helper) {
        this.mViewHelper = helper;
    }


    public void setUpEmptyView(View view) {
        mEmptyView = view;
        mEmptyView.setClickable(true);
    }

    public void setUpEmptyViewRes(int imgId,int tv1,int tv2) {
        if(mEmptyView!=null){
            ((ImageView)mEmptyView.findViewById(R.id.view_empty_image)) .setImageResource(imgId);
                ((TextView)mEmptyView.findViewById(R.id.view_empty_tv_1)) .setText(tv1);
            if(tv2!=0)
            ((TextView)mEmptyView.findViewById(R.id.view_empty_tv_2)) .setText(tv2);
            else {
                ((TextView)mEmptyView.findViewById(R.id.view_empty_tv_2)).setVisibility(View.GONE);
            }
        }
    }

    public void setUpNetWorkErrorView(View view, final NetWorkErrorListener netWorkErrorListener) {
        mNetWorkErrorView = view;
        mNetWorkErrorView.setClickable(true);
        View reload = view.findViewById(R.id.network_error_reload);
        View setting = view.findViewById(R.id.network_error_setting);
        if (netWorkErrorListener != null) {
            if (reload != null) {
                reload.setOnClickListener(view1 -> netWorkErrorListener.reTry());
            }
            if (setting != null) {
                setting.setOnClickListener(view12 -> netWorkErrorListener.onSettingNetWork());
            }
        }
    }

    public void setUpErrorView(View view, final NetWorkErrorListener netWorkErrorListener) {
        mErrorView = view;
        mErrorView.setClickable(true);
        View reload = view.findViewById(R.id.error_reload);
        if (netWorkErrorListener != null) {
            if (reload != null) {
                reload.setOnClickListener(view1 -> netWorkErrorListener.reTry());
            }
        }
    }

    public void setUpLoadingView(View view) {
        mLoadingView = view;
        mLoadingView.setClickable(true);
    }

    public void showEmptyView() {
        mViewHelper.showCaseLayout(mEmptyView);
    }

    public void showNetWorkErrorView() {
        mViewHelper.showCaseLayout(mNetWorkErrorView);
    }

    public void showLoadingView() {
        mViewHelper.showCaseLayout(mLoadingView);
    }

    public void showDataView() {
        mViewHelper.restoreLayout();
    }

    public void showErrorView() {
        mViewHelper.showCaseLayout(mErrorView);
    }


    public void releaseVaryView() {
        mErrorView = null;
        mLoadingView = null;
        mEmptyView = null;
    }

    public static class Builder {
        private View mErrorView;
        private View mLoadingView;
        private View mEmptyView;
        private View mDataView;
        private View mNetWorkErrorView;
        private NetWorkErrorListener netWorkErrorListener;


        public Builder setNetWorkErrorView(View netWorkErrorView) {
            mNetWorkErrorView = netWorkErrorView;
            return this;
        }

        public Builder setErrorView(View errorView) {
            mErrorView = errorView;
            return this;
        }

        public Builder setLoadingView(View loadingView) {
            mLoadingView = loadingView;
            return this;
        }

        public Builder setEmptyView(View emptyView) {
            mEmptyView = emptyView;
            return this;
        }

        public Builder setDataView(View dataView) {
            mDataView = dataView;
            return this;
        }

        public Builder setRefreshListener(NetWorkErrorListener netWorkErrorListener) {
            this.netWorkErrorListener = netWorkErrorListener;
            return this;
        }

        public VaryViewHelper build() {
            VaryViewHelper helper = new VaryViewHelper(mDataView);

            if (mNetWorkErrorView != null) {
                helper.setUpNetWorkErrorView(mNetWorkErrorView, netWorkErrorListener);
            }

            if (mEmptyView != null) {
                helper.setUpEmptyView(mEmptyView);
            }
            if (mErrorView != null) {
                helper.setUpErrorView(mErrorView, netWorkErrorListener);
            }
            if (mLoadingView != null) {
                helper.setUpLoadingView(mLoadingView);
            }
            return helper;
        }
    }

    public interface NetWorkErrorListener {
        void reTry();

        void onSettingNetWork();
    }

}
