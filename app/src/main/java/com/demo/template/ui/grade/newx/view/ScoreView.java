package com.demo.template.ui.grade.newx.view;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.android.library.bridge.core.base.IView;
import com.android.library.bridge.util.UIUtils;
import com.android.library.net.entity.template.ScoreTaskEntity;
import com.demo.template.entity.ScoreZipV2Entity;
import com.android.library.net.entity.template.TaskSubmitEntity;
import com.demo.template.R;
import com.demo.template.annotation.TouchMode;

/**
 * 打分BaseView
 */
public interface ScoreView extends IView<ScoreZipV2Entity> {
    /**
     * 第一次仲裁请求网络成功
     *
     * @param entity {@link ScoreZipV2Entity#getTaskList()}
     */
    void onArbitrationScoreFirstNetSuccess(@NonNull ScoreZipV2Entity entity);

    /**
     * 仲裁下一道题请求成功
     *
     * @param entity {@link ScoreZipV2Entity#getTaskList()}
     */
    void onArbitrationScoreNextNetSuccess(@NonNull ScoreZipV2Entity entity);

    /**
     * 切换仲裁下一套试题
     */
    void onArbitrationScoreNextNetAnother();

    /**
     * 仲裁没有下一题了
     */
    default void onArbitrationScoreNextNetNoMore() {
        UIUtils.show(R.string.grade_last_no_score_page);
    }

    /**
     * 仲裁上一道题请求成功
     *
     * @param entity {@link ScoreZipV2Entity#getTaskList()}
     */
    void onArbitrationScorePrevNetSuccess(@NonNull ScoreZipV2Entity entity);

    /**
     * 仲裁切换上一套试题
     */
    void onArbitrationScorePrevNetAnother();

    /**
     * 仲裁没有上一题了
     */
    default void onArbitrationScorePrevNetNoMore() {
        UIUtils.show(R.string.grade_first_no_score_page);
    }

    /**
     * 第一次请求网络成功
     *
     * @param entity {@link ScoreZipV2Entity#getTaskList()}
     */
    void onScoreFirstNetSuccess(@NonNull ScoreZipV2Entity entity);

    /**
     * 下一道题请求成功
     *
     * @param entity {@link ScoreZipV2Entity#getTaskList()}
     */
    void onScoreNextNetSuccess(@NonNull ScoreZipV2Entity entity);

    /**
     * 切换下一套试题
     */
    void onScoreNextNetAnother();

    /**
     * 没有下一题了
     */
    default void onScoreNextNetNoMore() {
        UIUtils.show(R.string.grade_last_no_score_page);
    }

    /**
     * 上一道题请求成功
     *
     * @param entity {@link ScoreZipV2Entity#getTaskList()}
     */
    void onScorePrevNetSuccess(@NonNull ScoreZipV2Entity entity);

    /**
     * 切换上一套试题
     */
    void onScorePrevNetAnother();

    /**
     * 没有上一题了
     */
    default void onScorePrevNetNoMore() {
        UIUtils.show(R.string.grade_first_no_score_page);
    }

    /**
     * 只显示未阅卷请求成功
     *
     * @param entity {@link ScoreZipV2Entity#getTaskList()}
     */
    void onScoreUnNetSuccess(@NonNull ScoreZipV2Entity entity);

    /**
     * 只显示未阅卷关闭失败
     */
    void onShowFirstError();

    /**
     * 只显示未阅卷请求失败
     */
    void onShowUnError();

    /**
     * 暂无未阅卷
     */
    void onShowUnEmpty();

    /**
     * 只显示阅卷下一题请求成功
     *
     * @param entity {@link ScoreZipV2Entity#getTaskList()}
     */
    void onScoreUnNextNetSuccess(@NonNull ScoreZipV2Entity entity);

    /**
     * 只显示未阅卷下一套题
     */
    void onScoreUnNextNetAnother();

    /**
     * 只显示未阅卷没有下一题了
     */
    default void onScoreUnNextNetNoMore() {
        UIUtils.show(R.string.grade_last_no_score_page);
    }

    /**
     * 问题卷请求成功
     *
     * @param entity {@link ScoreZipV2Entity#getTaskList()}
     */
    void onScoreProblemNetSuccess(@NonNull ScoreZipV2Entity entity);

    /**
     * 问题卷下一套题请求成功
     *
     * @param entity {@link ScoreTaskEntity}
     */
    void onScoreProblemNextAnother(@NonNull ScoreTaskEntity entity);

    /**
     * 问题卷没有下一题了
     */
    default void onScoreProblemNextNetNoMore() {
        UIUtils.show(R.string.grade_last_no_score_page);
    }

    /**
     * 问题卷上一套题请求成功
     *
     * @param entity {@link ScoreTaskEntity}
     */
    void onScoreProblemPrevNetAnother(@NonNull ScoreTaskEntity entity);

    /**
     * 问题卷没有上一题了
     */
    default void onScoreProblemPrevNetNoMore() {
        UIUtils.show(R.string.grade_first_no_score_page);
    }

    /**
     * 提交成功
     *
     * @param entity   {@link TaskSubmitEntity}
     * @param fraction 用来区分是否是全对或者全错或者是否是问题卷
     * @param type     上一页还是下一页的自动提交 {@link TouchMode}
     */
    void onSubmitSuccess(@NonNull TaskSubmitEntity entity, @NonNull String fraction, @TouchMode int type);

    /**
     * 提交异常
     */
    default void onSubmitError() {
    }

    /**
     * {@link Activity}
     */
    @NonNull
    Activity getCurrentActivity();
}
