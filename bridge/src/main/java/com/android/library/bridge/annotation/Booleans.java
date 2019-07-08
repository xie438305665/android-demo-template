package com.android.library.bridge.annotation;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author xcl
 * @create 2019/4/1
 */
@IntDef({
        Booleans.TRUE,
        Booleans.FALSE,
})
@Retention(RetentionPolicy.SOURCE)
public @interface Booleans {
    int TRUE = 1;
    int FALSE = 0;
}
