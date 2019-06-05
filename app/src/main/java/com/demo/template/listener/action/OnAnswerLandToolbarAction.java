package com.demo.template.listener.action;

import android.support.annotation.NonNull;

import com.android.library.bridge.core.base.BaseAction;

/**
 * @author y
 * @create 2019/4/1
 */
public interface OnAnswerLandToolbarAction extends BaseAction {
    /**
     * 更多分
     */
    void onAnswerLandToolbarOpenDrawer();

    /**
     * 问题卷
     */
    void onAnswerLandToolbarIssues();

    /**
     * 销毁页面
     */
    void onAnswerLandToolbarFinish();

    /**
     * 输入框变化
     *
     * @param landFraction 当前分数
     */
    void onAnswerLandToolbarKeyboardChanged(@NonNull String landFraction);
}