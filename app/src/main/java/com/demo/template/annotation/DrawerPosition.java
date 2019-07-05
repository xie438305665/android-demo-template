package com.demo.template.annotation;

import android.support.annotation.IntDef;

/**
 * @author xcl
 */
@IntDef({
        DrawerPosition.POSITION_HEAD,
        DrawerPosition.POSITION_ITEM,
        DrawerPosition.POSITION_FOOTER,
})
public @interface DrawerPosition {
    /**
     * 头部
     */
    int POSITION_HEAD = -1;
    /**
     * 试题详情
     */
    int POSITION_ITEM = 0;
    /**
     * 每屏显示数量
     */
    int POSITION_FOOTER = 1;
}
