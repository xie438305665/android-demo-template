package com.demo.template.ui.grade.oldx.view;

import android.support.annotation.NonNull;

import com.android.library.net.entity.template.AnnotationEntity;

import java.io.File;
import java.util.List;

/**
 * @author y
 * 解答题
 */
@Deprecated
public interface OldAnswerScoreView extends OldScoreView {
    /**
     * 问题卷
     */
    void onIssues();

    /**
     * 还原试题成功
     *
     * @param file 试题缓存
     */
    void onResetImageSuccess(@NonNull File file);

    /**
     * 还原试题失败
     */
    void onResetImageError();

    /**
     * 自定义批注请求成功
     *
     * @param entities 数据
     */
    void onAnnotationSuccess(@NonNull List<AnnotationEntity> entities);

    /**
     * 更多分数
     */
    void showFractionMore();
}
