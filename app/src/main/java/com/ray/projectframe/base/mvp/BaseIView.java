package com.ray.projectframe.base.mvp;

import io.reactivex.disposables.Disposable;

/**
 * Created by ray on 17/5/11.
 */
public interface BaseIView {
    void showLoading();
    void showNetWorkErrorView();
    void showErrorView();
    void showDataView();
    void showEmptyView();
    void addRxDestroy(Disposable d);//添加rx管理
}
