package com.demo.template.ui.template.oldx.impl;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.library.bridge.annotation.QuestionType;
import com.android.library.bridge.core.base.IPresenter;
import com.android.library.bridge.key.Booleans;
import com.android.library.net.body.read.SubmitScoreBody;

import java.util.ArrayList;

@SuppressWarnings("JavaDoc")
@Deprecated
public interface OldScorePresenter extends IPresenter {

    /**
     * 批注网络请求
     */
    void onAnnotationRequest();

    /**
     * 第一次网络请求
     *
     * @param examGroupId
     * @param markingGroupId
     * @param classId
     * @param type
     * @param first
     * @param hasPreLoading
     */
    void onScoreRequest(@NonNull String examGroupId, @NonNull String markingGroupId, @Nullable String classId, @QuestionType int type, boolean first, @Booleans int hasPreLoading);

    /**
     * 下一页网络请求
     *
     * @param examGroupId
     * @param markingGroupId
     * @param classId
     * @param studentId
     * @param type
     * @param hasPreLoading
     */
    void onScoreNextRequest(@NonNull String examGroupId, @NonNull String markingGroupId, @Nullable String classId, @NonNull String studentId, @QuestionType int type, @Booleans int hasPreLoading);

    /**
     * 上一页网络请求
     *
     * @param examGroupId
     * @param markingGroupId
     * @param classId
     * @param studentId
     * @param type
     * @param hasPreLoading
     */
    void onScorePrevRequest(@NonNull String examGroupId, @NonNull String markingGroupId, @Nullable String classId, @NonNull String studentId, @QuestionType int type, @Booleans int hasPreLoading);

    /**
     * 只显示未阅卷网络请求
     *
     * @param examGroupId
     * @param markingGroupId
     * @param classId
     * @param type
     * @param hasPreLoading
     */
    void onScoreUnRequest(@NonNull String examGroupId, @NonNull String markingGroupId, @Nullable String classId, @QuestionType int type, @Booleans int hasPreLoading);

    /**
     * 只显示未阅卷下一页网络请求
     *
     * @param examGroupId
     * @param markingGroupId
     * @param classId
     * @param type
     * @param hasPreLoading
     */
    void onScoreUnNextRequest(@NonNull String examGroupId, @NonNull String markingGroupId, @Nullable String classId, @QuestionType int type, @Booleans int hasPreLoading);

    /**
     * 问题卷初次请求
     *
     * @param examGroupId
     * @param markingGroupId
     * @param studentId
     * @param type
     * @param first
     * @param hasPreLoading
     */
    void onScoreProblemRequest(@NonNull String examGroupId, @NonNull String markingGroupId, @NonNull String studentId, @QuestionType int type, boolean first, @Booleans int hasPreLoading);

    /**
     * 问题卷下一页网络请求
     *
     * @param examGroupId
     * @param markingGroupId
     * @param studentId
     * @param type
     * @param hasPreLoading
     */
    void onScoreProblemNextRequest(@NonNull String examGroupId, @NonNull String markingGroupId, @NonNull String studentId, @QuestionType int type, @Booleans int hasPreLoading);

    /**
     * 问题卷上一页网络请求
     *
     * @param examGroupId
     * @param markingGroupId
     * @param studentId
     * @param type
     * @param hasPreLoading
     */
    void onScoreProblemPrevRequest(@NonNull String examGroupId, @NonNull String markingGroupId, @NonNull String studentId, @QuestionType int type, @Booleans int hasPreLoading);

    /**
     * 正常提交打分数据
     *
     * @param examGroupId
     * @param markingGroupId
     * @param classId
     * @param list
     * @param fraction
     * @param type
     */
    void onSubmit(@NonNull String examGroupId, @NonNull String markingGroupId, @Nullable String classId, @NonNull ArrayList<SubmitScoreBody> list, @NonNull String fraction, @QuestionType int type);

    /**
     * 问题卷提交打分数据
     *
     * @param examGroupId
     * @param markingGroupId
     * @param classId
     * @param bodyList
     * @param fraction
     * @param type
     */
    void onProblemSubmit(@NonNull String examGroupId, @NonNull String markingGroupId, @Nullable String classId, @NonNull ArrayList<SubmitScoreBody> bodyList, @NonNull String fraction, @QuestionType int type);

    /**
     * 重置解答题图片
     *
     * @param id
     */
    void onScoreResetImage(@NonNull String id);
}
