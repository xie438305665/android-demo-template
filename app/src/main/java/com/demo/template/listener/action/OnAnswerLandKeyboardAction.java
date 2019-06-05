package com.demo.template.listener.action;

import android.support.annotation.NonNull;

import com.android.library.bridge.core.base.BaseAction;

/**
 * @author y
 * @create 2019/4/1
 */
public interface OnAnswerLandKeyboardAction extends BaseAction {
    void onAnswerLandSubmit(@NonNull String fraction);
}
