package com.demo.template.listener.read;

import android.support.annotation.Nullable;

import com.android.library.bridge.annotation.QuestionType;
import com.demo.template.widget.ScoreGuideView;

/**
 * @author y
 * @create 2019/4/4
 */
public interface OnScoreGuideOwnListener {
    /**
     * 初始化引导view
     *
     * @return {@link ScoreGuideView}
     */
    @Nullable
    ScoreGuideView newInstanceGuideView();

    /**
     * 显示引导页
     *
     * @param scoreType 试题type {@link QuestionType}
     */
    void showGuideView(@QuestionType int scoreType);

    /**
     * 是否显示引导页
     *
     * @param scoreType 试题type  {@link QuestionType}
     * @return true 显示
     */
    boolean hasShowGuideView(@QuestionType int scoreType);
}
