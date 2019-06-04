package com.demo.template.annotation;

import android.support.annotation.IntDef;

/**
 * @author xcl
 */
@IntDef({
        DrawerPosition.SETTING,
        DrawerPosition.QUESTIONS_DETAIL,
        DrawerPosition.FILL_NUM,
        DrawerPosition.KEYBOARD,
        DrawerPosition.LAND,
        DrawerPosition.UN_SCORE,
        DrawerPosition.AUTOMATIC_SUBMIT,
})
public @interface DrawerPosition {
    /**
     * 头部
     */
    int SETTING = -1;
    /**
     * 试题详情
     */
    int QUESTIONS_DETAIL = 0;
    /**
     * 每屏显示数量
     */
    int FILL_NUM = 1;
    /**
     * 键盘设置
     */
    int KEYBOARD = 2;
    /**
     * 横屏
     */
    int LAND = 3;
    /**
     * 只显示未阅卷
     */
    int UN_SCORE = 4;
    /**
     * 自动提交
     */
    int AUTOMATIC_SUBMIT = 5;
}
