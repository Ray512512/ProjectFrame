package com.ray.projectframe.ui.viewhelper;

import android.view.View;

import frame.project.ray.projectframe.R;


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

    private View mTransferMsgView;//消息中转

    private View mTransferMineView;//我的中转


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

    /*//个人中心未登录点击事件
    public void setUpTransferView(View view, TransferListener transferListener, boolean isMineTransfer) {
        mTransferMineView = view;
        ImageView action = (ImageView) view.findViewById(R.id.not_logged_personalcenter);
//        ViewGroup.LayoutParams ivParams = action.getLayoutParams();
//        int width = CommonTools.getDisplayWidth(view.getContext());
//        ivParams.width = width;
//        ivParams.height = width;
//        action.setLayoutParams(ivParams);
        if (transferListener != null) {
            action.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    transferListener.onLoginAction();
                }
            });
        }
    }*/

    public void setUpNetWorkErrorView(View view, final NetWorkErrorListener netWorkErrorListener) {
        mNetWorkErrorView = view;
        mNetWorkErrorView.setClickable(true);
        View reload = view.findViewById(R.id.network_error_reload);
        View setting = view.findViewById(R.id.network_error_setting);
        if (netWorkErrorListener != null) {
            if (reload != null) {
                reload.setOnClickListener(view1 -> netWorkErrorListener.onRefresh());
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
                reload.setOnClickListener(view1 -> netWorkErrorListener.onRefresh());
            }
        }
    }

    public void setUpLoadingView(View view) {
        mLoadingView = view;
        mLoadingView.setClickable(true);
    }

    public void showEmptyView() {
        mViewHelper.showCaseLayout(mEmptyView);
        stopProgressLoading();
    }

    public void showNetWorkErrorView() {
        mViewHelper.showCaseLayout(mNetWorkErrorView);
        stopProgressLoading();
    }

    public void showLoadingView() {
        mViewHelper.showCaseLayout(mLoadingView);
        startProgressLoading();
    }

    public void showDataView() {
        mViewHelper.restoreLayout();
        stopProgressLoading();
    }

    public void showErrorView() {
        mViewHelper.showCaseLayout(mErrorView);
        stopProgressLoading();
    }

//    public void showTransferMsgView() {
//        mViewHelper.showCaseLayout(mTransferMsgView);
//    }

    public void showTransferMineView() {
        mViewHelper.showCaseLayout(mTransferMineView);
    }

    private void stopProgressLoading() {
//        if (mLoadingProgress != null && mLoadingProgress.isSpinning()) {
//            mLoadingProgress.stopSpinning();
//        }
    }

    private void startProgressLoading() {
//        if (mLoadingProgress != null && !mLoadingProgress.isSpinning()) {
//            mLoadingProgress.spin();
//        }
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
        private View mTransferMsgView;
        private View mTransferMineView;
        private NetWorkErrorListener netWorkErrorListener;
        private TransferListener transferListener;


        public Builder setNetWorkErrorView(View netWorkErrorView) {
            mNetWorkErrorView = netWorkErrorView;
            return this;
        }

        public Builder setTransferMsgView(View transferMsgView) {
            mTransferMsgView = transferMsgView;
            return this;
        }

        public Builder setTransferMineView(View transferMineView) {
            mTransferMineView = transferMineView;
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

        public Builder setTransferListener(TransferListener transferListener) {
            this.transferListener = transferListener;
            return this;
        }

        public VaryViewHelper build() {
            VaryViewHelper helper = new VaryViewHelper(mDataView);

           /* if (mTransferMineView != null) {
                helper.setUpTransferView(mTransferMineView, transferListener, true);
            }

            if (mTransferMsgView != null) {
                helper.setUpTransferView(mTransferMsgView, transferListener, false);
            }*/

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
        void onRefresh();

        void onSettingNetWork();
    }

    public interface TransferListener {
        void onLoginAction();
    }

}
