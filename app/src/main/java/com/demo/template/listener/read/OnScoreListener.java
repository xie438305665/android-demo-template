package com.demo.template.listener.read;

import android.support.annotation.NonNull;

import com.android.library.net.entity.template.ScoreParameterEntity;

/**
 * by y.
 * <p>
 * Description:
 */
public interface OnScoreListener {
    /**
     * 切换到下一题
     *
     * @param hasNext 是否是下一题
     */
    void replaceFragment(@NonNull ScoreParameterEntity entity, boolean hasNext);

    /**
     * 更新第几题
     */
    void onRefreshTitle();

    /**
     * 更新阅卷进度和正确率
     */
    void onRefreshPercentageAndSchedule();

    /**
     * 更新键盘
     */
    void onRefreshKeyboard();

    /**
     * 更新解答题标题栏
     */
    void onRefreshAnswerMaxScore();

    /**
     * 更新解答题名称,
     */
    void onRefreshAnswerName();

    /**
     * 更新解答题分数
     */
    void onRefreshAnswerFraction();

    /**
     * 取消解答题输入分数框焦点
     */
    void onClearAnswerEditTextFocus();

    /**
     * 竖屏获取 解答题输入分数
     *
     * @return 分数
     */
    @NonNull
    String getAnswerFraction();
}
