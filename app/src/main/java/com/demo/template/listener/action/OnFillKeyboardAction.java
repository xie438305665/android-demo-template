package com.demo.template.listener.action;

import android.support.annotation.NonNull;

import com.android.library.bridge.core.base.BaseAction;

/**
 * @author y
 * @create 2019/4/1
 */
public interface OnFillKeyboardAction extends BaseAction {

    /**
     * 对
     */
    void onFillKeyboardRight();

    /**
     * 错
     */
    void onFillKeyboardWrong();

    /**
     * 问题卷
     */
    void onFillKeyboardUnKnown();

    /**
     * 打分
     */
    void onFillKeyboardSubmit(@NonNull String fraction);

}
