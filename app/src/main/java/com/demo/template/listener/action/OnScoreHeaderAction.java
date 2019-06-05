package com.demo.template.listener.action;

import android.support.annotation.NonNull;

import com.android.library.bridge.core.base.BaseAction;

/**
 * @author y
 * @create 2019/4/1
 */
public interface OnScoreHeaderAction extends BaseAction {
    /**
     * 全错
     */
    void onScoreHeaderAllFalse();

    /**
     * 全对
     */
    void onScoreHeaderAllTrue();

    /**
     * 查看答案
     */
    void onScoreHeaderAnswer();

    /**
     * 分数输入
     *
     * @param fraction 分数
     */
    void onScoreFractionKeyboardChanged(@NonNull String fraction);
}
