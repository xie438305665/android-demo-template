package com.demo.template.ui.read.oldx.view;

import android.support.annotation.NonNull;

/**
 * @author y
 * 填空题
 */
@Deprecated
public interface OldFillScoreView extends OldScoreView {

    /**
     * 每屏显示数量
     */
    void onShowItem();

    /**
     * 全部提交
     *
     * @param hasTrue 是否全对
     */
    void onSubmitAll(boolean hasTrue);

    /**
     * 单个提交
     *
     * @param fraction 分数
     */
    void onSubmit(@NonNull String fraction);

}
