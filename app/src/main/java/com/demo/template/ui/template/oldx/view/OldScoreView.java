package com.demo.template.ui.template.oldx.view;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.android.library.bridge.core.base.IView;
import com.android.library.bridge.util.UIUtils;
import com.android.library.net.entity.template.ScoreEntity;
import com.android.library.net.entity.template.ScoreNextEntity;
import com.android.library.net.entity.template.ScorePrevEntity;
import com.demo.template.entity.ScoreZipEntity;
import com.demo.template.R;
import com.demo.template.annotation.TouchMode;

/**
 * 打分BaseView
 */
@Deprecated
public interface OldScoreView extends IView<ScoreZipEntity> {
    /**
     * 第一次请求网络成功
     *
     * @param entity {@link ScoreZipEntity#getStudentFirst()}
     */
    void onScoreFirstNetSuccess(@NonNull ScoreZipEntity entity);

    /**
     * 下一道题请求成功
     *
     * @param entity {@link ScoreZipEntity#getStudentNext()}
     */
    void onScoreNextNetSuccess(@NonNull ScoreZipEntity entity);

    /**
     * 切换下一套试题
     *
     * @param next {@link com.android.library.net.entity.template.ScoreNextEntity}
     */
    void onScoreNextNetAnother(@NonNull ScoreNextEntity.NextBean next);

    /**
     * 没有下一题了
     */
    default void onScoreNextNetNoMore() {
        UIUtils.show(R.string.grade_last_no_score_page);
    }

    /**
     * 上一道题请求成功
     *
     * @param entity {@link ScoreZipEntity#getStudentPrev()}
     */
    void onScorePrevNetSuccess(@NonNull ScoreZipEntity entity);

    /**
     * 切换上一套试题
     *
     * @param prev {@link ScorePrevEntity}
     */
    void onScorePrevNetAnother(@NonNull ScorePrevEntity.PrevBean prev);

    /**
     * 没有上一题了
     */
    default void onScorePrevNetNoMore() {
        UIUtils.show(R.string.grade_first_no_score_page);
    }

    /**
     * 只显示未阅卷请求成功
     *
     * @param entity {@link ScoreZipEntity#getStudentFirst()}
     */
    void onScoreUnNetSuccess(@NonNull ScoreZipEntity entity);

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
     * @param entity {@link ScoreZipEntity#getStudentNext()}
     */
    void onScoreUnNextNetSuccess(@NonNull ScoreZipEntity entity);

    /**
     * 只显示未阅卷下一套题
     *
     * @param next {@link ScoreNextEntity}
     */
    void onScoreUnNextNetAnother(@NonNull ScoreNextEntity.NextBean next);

    /**
     * 只显示未阅卷没有下一题了
     */
    default void onScoreUnNextNetNoMore() {
        UIUtils.show(R.string.grade_last_no_score_page);
    }

    /**
     * 问题卷请求成功
     *
     * @param entity {@link ScoreZipEntity#getStudentFirst()}
     */
    void onScoreProblemNetSuccess(@NonNull ScoreZipEntity entity);

    /**
     * 问题卷下一套题请求成功
     *
     * @param entity {@link ScoreEntity}
     */
    void onScoreProblemNextAnother(@NonNull ScoreEntity entity);

    /**
     * 问题卷没有下一题了
     */
    default void onScoreProblemNextNetNoMore() {
        UIUtils.show(R.string.grade_last_no_score_page);
    }

    /**
     * 问题卷上一套题请求成功
     *
     * @param entity {@link ScoreEntity}
     */
    void onScoreProblemPrevNetAnother(@NonNull ScoreEntity entity);

    /**
     * 问题卷没有上一题了
     */
    default void onScoreProblemPrevNetNoMore() {
        UIUtils.show(R.string.grade_first_no_score_page);
    }

    /**
     * 提交成功
     *
     * @param entity   {@link ScoreZipEntity#getGroupQuota()}
     * @param fraction 用来区分是否是全对或者全错或者是否是问题卷
     * @param type     上一页还是下一页的自动提交 {@link TouchMode}
     */
    void onSubmitSuccess(@NonNull ScoreZipEntity entity, @NonNull String fraction, @TouchMode int type);

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
