package com.android.library.bridge.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author xcl
 * @create 2019/4/1
 */
@IntDef({
        UIType.STATUS,
        UIType.NOT_STATUS,
})
@Retention(RetentionPolicy.SOURCE)
public @interface UIType {
    /**
     * 使用{@link com.status.layout.StatusLayout}
     */
    int STATUS = 0;
    /**
     * 显示Loading{@link com.android.library.widget.custom.CustomLoadingView}
     */
    int NOT_STATUS = 2;
}
