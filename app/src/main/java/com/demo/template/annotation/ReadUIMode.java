package com.demo.template.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author xcl
 */
@IntDef({
        ReadUIMode.INIT,
        ReadUIMode.REPLACE,
        ReadUIMode.LANDSCAPE
})
@Retention(RetentionPolicy.SOURCE)
public @interface ReadUIMode {
    /**
     * 初始化
     */
    int INIT = 0;
    /**
     * 切换
     */
    int REPLACE = 1;
    /**
     * 横竖屏
     */
    int LANDSCAPE = 2;
}
