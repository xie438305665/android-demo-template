package com.demo.template.annotation;

import android.support.annotation.IntDef;

import com.xadapter.adapter.multi.MultiCallBack;

/**
 * @author xcl
 */
@IntDef({
        MultiItemType.HEADER,
        MultiItemType.ITEM,
        MultiItemType.FOOTER,
})
public @interface MultiItemType {
    /**
     * 头部
     */
    int HEADER = 0;
    /**
     * item_list_template
     */
    int ITEM = MultiCallBack.TYPE_ITEM;
    /**
     * 尾部
     */
    int FOOTER = 1;
}
