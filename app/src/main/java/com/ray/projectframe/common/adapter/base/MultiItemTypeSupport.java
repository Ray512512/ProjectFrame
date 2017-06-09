package com.ray.projectframe.common.adapter.base;

/**
 * Created by HanHailong on 15/9/6.
 */
public interface MultiItemTypeSupport<T> {

    int getLayoutId(int viewType);

    int getItemViewType(int position, T t);

}
