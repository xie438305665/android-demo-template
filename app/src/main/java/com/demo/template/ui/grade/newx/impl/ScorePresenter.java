package com.demo.template.ui.grade.newx.impl;

import android.support.annotation.NonNull;

import com.android.library.bridge.annotation.QuestionType;
import com.android.library.bridge.core.base.IPresenter;
import com.android.library.bridge.key.Booleans;
import com.android.library.net.body.read.ArbitrationTaskSubmitBody;
import com.android.library.net.body.read.ProblemTaskSubmitBody;
import com.android.library.net.body.read.TaskSubmitBody;
import com.demo.template.annotation.TouchMode;

@SuppressWarnings("JavaDoc")
public interface ScorePresenter extends IPresenter {

    /**
     * 批注网络请求
     */
    void onAnnotationRequest();

    /**
     * 第一次网络请求
     *
     * @param examGroupId
     * @param topicId
     * @param type
     * @param first
     * @param pageIndex
     * @param hasPreLoading
     */
    void onScoreRequest(@NonNull String examGroupId, @NonNull String topicId, @QuestionType int type, int pageIndex, boolean first, @Booleans int hasPreLoading);

    /**
     * 下一页网络请求
     *
     * @param examGroupId
     * @param topicId
     * @param type
     * @param first
     * @param pageIndex
     * @param hasPreLoading
     */
    void onScoreNextRequest(@NonNull String examGroupId, @NonNull String topicId, @QuestionType int type, int pageIndex, boolean first, @Booleans int hasPreLoading);

    /**
     * 上一页网络请求
     *
     * @param examGroupId
     * @param topicId
     * @param type
     * @param first
     * @param pageIndex
     * @param hasPreLoading
     */
    void onScorePrevRequest(@NonNull String examGroupId, @NonNull String topicId, @QuestionType int type, int pageIndex, boolean first, @Booleans int hasPreLoading);

    /**
     * 仲裁网络请求
     *
     * @param examGroupId
     * @param topicId
     * @param type
     * @param first
     * @param pageIndex
     * @param hasPreLoading
     */
    void onArbitrationScoreRequest(@NonNull String examGroupId, @NonNull String topicId, @QuestionType int type, int pageIndex, boolean first, @Booleans int hasPreLoading);

    /**
     * 仲裁下一页网络请求
     *
     * @param examGroupId
     * @param topicId
     * @param type
     * @param first
     * @param pageIndex
     * @param hasPreLoading
     */
    void onArbitrationScoreNextRequest(@NonNull String examGroupId, @NonNull String topicId, @QuestionType int type, int pageIndex, boolean first, @Booleans int hasPreLoading);

    /**
     * 仲裁上一页网络请求
     *
     * @param examGroupId
     * @param topicId
     * @param type
     * @param first
     * @param pageIndex
     * @param hasPreLoading
     */
    void onArbitrationScorePrevRequest(@NonNull String examGroupId, @NonNull String topicId, @QuestionType int type, int pageIndex, boolean first, @Booleans int hasPreLoading);

    /**
     * 只显示未阅卷网络请求
     *
     * @param examGroupId
     * @param topicId
     * @param type
     * @param hasPreLoading
     */
    void onScoreUnRequest(@NonNull String examGroupId, @NonNull String topicId, @QuestionType int type, @Booleans int hasPreLoading);

    /**
     * 只显示未阅卷下一页网络请求
     *
     * @param examGroupId
     * @param topicId
     * @param type
     * @param hasPreLoading
     */
    void onScoreUnNextRequest(@NonNull String examGroupId, @NonNull String topicId, @QuestionType int type, @Booleans int hasPreLoading);

    /**
     * 问题卷初次请求
     *
     * @param examGroupId
     * @param topicId
     * @param studentId
     * @param type
     * @param first
     * @param hasPreLoading
     */
    void onScoreProblemRequest(@NonNull String examGroupId, @NonNull String topicId, @NonNull String studentId, @QuestionType int type, boolean first, @Booleans int hasPreLoading);

    /**
     * 问题卷下一页网络请求
     *
     * @param examGroupId
     * @param topicId
     * @param studentId
     * @param type
     * @param hasPreLoading
     */
    void onScoreProblemNextRequest(@NonNull String examGroupId, @NonNull String topicId, @NonNull String studentId, @QuestionType int type, boolean first, @Booleans int hasPreLoading);

    /**
     * 问题卷上一页网络请求
     *
     * @param examGroupId
     * @param topicId
     * @param studentId
     * @param type
     * @param hasPreLoading
     */
    void onScoreProblemPrevRequest(@NonNull String examGroupId, @NonNull String topicId, @NonNull String studentId, @QuestionType int type, boolean first, @Booleans int hasPreLoading);

    /**
     * 正常提交打分数据
     *
     * @param fraction
     * @param type
     */
    void onSubmit(@NonNull TaskSubmitBody taskSubmitBody, @NonNull String fraction, @TouchMode int type);

    /**
     * 问题卷提交打分数据
     *
     * @param body
     * @param fraction
     * @param type
     */
    void onProblemSubmit(@NonNull ProblemTaskSubmitBody body, @NonNull String fraction, @TouchMode int type);

    /**
     * 仲裁提交打分数据
     *
     * @param body
     * @param fraction
     * @param type
     */
    void onArbitrationSubmit(@NonNull ArbitrationTaskSubmitBody body, @NonNull String fraction, @TouchMode int type);

    /**
     * 重置解答题图片
     *
     * @param id
     */
    void onScoreResetImage(@NonNull String id);
}
