package com.demo.template.annotation;

import android.support.annotation.IntDef;

/**
 * @author xcl
 */
@IntDef({
        TouchMode.NEXT,
        TouchMode.PREV,
        TouchMode.UNKNOWN,
})
public @interface TouchMode {
    /**
     * 上一道题
     */
    int PREV = -11;
    /**
     * 下一道题
     */
    int NEXT = -12;
    /**
     * 未知
     */
    int UNKNOWN = -1;
}
