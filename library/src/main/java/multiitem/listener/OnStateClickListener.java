package multiitem.listener;

import multiitem.helper.StateViewHelper;
import multiitem.item.BaseItemState;

/**
 * 状态页中的点击Listener
 * Created by free46000 on 2017/4/23.
 *
 * @see BaseItemState
 * @see StateViewHelper
 */
@FunctionalInterface
public interface OnStateClickListener {
    void onStateClick();
}
