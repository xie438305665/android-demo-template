package com.demo.template.annotation;

import android.support.annotation.IntDef;

import com.xadapter.adapter.multi.MultiCallBack;

/**
 * @author xcl
 */
@IntDef({
        DrawerItemViewType.HEADER,
        DrawerItemViewType.ITEM,
})
public @interface DrawerItemViewType {
    /**
     * 头部
     */
    int HEADER = 0;
    /**
     * item
     */
    int ITEM = MultiCallBack.TYPE_ITEM;
}
