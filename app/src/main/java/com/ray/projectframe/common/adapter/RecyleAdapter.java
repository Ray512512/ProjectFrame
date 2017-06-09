package com.ray.projectframe.common.adapter;

import android.content.Context;

import java.util.List;

import com.ray.projectframe.common.adapter.base.BaseAdapterHelper;
import com.ray.projectframe.common.adapter.base.BaseQuickAdapter;
import com.ray.projectframe.common.adapter.base.MultiItemTypeSupport;


/**
 * Created by HanHailong on 15/9/6.
 */
public abstract class RecyleAdapter<T> extends BaseQuickAdapter<T, BaseAdapterHelper> {

    /**
     * Create a QuickAdapter.
     *
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     */
    public RecyleAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization mData.
     *
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     * @param data        A new list is created out of this one to avoid mutable list
     */
    public RecyleAdapter(Context context, int layoutResId, List<T> data) {
        super(context, layoutResId, data);
    }

    /**
     * Create a multi-view-type QuickAdapter
     *
     * @param context              The context
     * @param multiItemTypeSupport multiitemtypesupport
     */
    protected RecyleAdapter(Context context, MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, multiItemTypeSupport);
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,MultiItemTypeSupport) but with
     * some initialization mData
     *
     * @param context
     * @param multiItemTypeSupport
     * @param data
     */
    protected RecyleAdapter(Context context, MultiItemTypeSupport<T> multiItemTypeSupport, List<T> data) {
        super(context, multiItemTypeSupport, data);
    }

}
