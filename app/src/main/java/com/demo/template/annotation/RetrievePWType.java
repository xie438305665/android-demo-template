package com.demo.template.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author xcl
 * @create 2019/6/4
 */
@IntDef({
        RetrievePWType.FILL_USER,
        RetrievePWType.VERIFICATION,
        RetrievePWType.SETTING_PW
})
@Retention(RetentionPolicy.SOURCE)
public @interface RetrievePWType {
    int FILL_USER = 0;
    int VERIFICATION = 1;
    int SETTING_PW = 2;
}
