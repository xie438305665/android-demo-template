package com.demo.template.listener.action;

import com.android.library.bridge.core.base.BaseAction;

/**
 * @author y
 * @create 2019/4/1
 */
public interface OnAnswerPortKeyboardAction extends BaseAction {

    /**
     * 问题卷
     */
    void onAnswerPortIssues();

    /**
     * 更多分
     */
    void onAnswerPortMoreFraction();

    /**
     * 打分
     *
     * @param fraction 分数
     */
    void onAnswerPortFractionSubmit(String fraction);

}