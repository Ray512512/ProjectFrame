package com.ray.library.view.view.recyclerviewHelper;

import android.support.v7.util.DiffUtil;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ray on 2017/12/23.
 */

public class DiffCallback<T> extends DiffUtil.Callback {
    protected List<T> mOldL, mNewL;
    private int type=-1;

 /*   public DiffCallback(List<T> oldL, List<T> newL) {
        this.mOldL = oldL;
        this.mNewL = newL;
    }*/


 /**
  * 列表数据
  * */
    public void setData(int type, List<T> o, List<T> n) {
        this.type=type;
        mOldL = o;
        mNewL = n;
    }


    /**
     * 单个数据
     * */
    public void setData(int type,T o, T n) {
        this.type=type;
        List<T> os=new ArrayList<T>();
        List<T> on=new ArrayList<T>();
        os.add(o);on.add(n);
        mOldL = os;
        mNewL = on;
    }


    @Override
    public int getOldListSize() {
        return mOldL == null ? 0 : mOldL.size();
    }

    @Override
    public int getNewListSize() {
        return mNewL == null ? 0 : mNewL.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Object oldD=mOldL.get(oldItemPosition);
        Object newD=mNewL.get(newItemPosition);
        if(oldD==null)return false;
        if(newD==null)return false;
        return /*DataUtils.isSame(type,oldD,newD)*/false;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }
}
