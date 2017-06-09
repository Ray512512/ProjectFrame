package com.ray.projectframe.ui.view.baseadapter.entity;

import java.io.Serializable;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public abstract class MultiItemEntity implements Serializable{
    protected int itemType;

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
