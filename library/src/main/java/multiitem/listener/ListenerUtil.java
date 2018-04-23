package multiitem.listener;

import android.util.Log;
import android.view.View;

import multiitem.adapter.holder.BaseViewHolder;
import multiitem.common.Const;

/**
 * @author free46000  2017/03/16
 * @version v1.0
 */
public class ListenerUtil {
    /**
     * 通过点击的item view获取到BaseViewHolder
     *
     * @return BaseViewHolder
     */
    public static BaseViewHolder getViewHolderByItemView(View view) {
        Object tag = view.getTag(Const.VIEW_HOLDER_TAG);
        if (tag == null || !(tag instanceof BaseViewHolder)) {
            Log.e("BaseViewHolder", "没有通过item view的tag没获取到ViewHolder");
            return null;
        }
        return (BaseViewHolder) tag;
    }

}
