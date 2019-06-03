package com.android.library.bridge.annotation;

import android.support.annotation.IntDef;

/**
 * @author xcl
 * @create 2019/4/1
 */
@IntDef({
        VersionType.V1,
        VersionType.V2,
})
public @interface VersionType {
    int V1 = 1;
    int V2 = 2;
}
