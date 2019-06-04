package com.demo.template.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author xcl
 * <p>
 * <p>
 * 对应{@link GradeScoreType},针对网络请求做好滑动之前的准备,
 * 具体状态回调可参考{@link OldScoreView}
 * 具体状态回调可参考{@link com.zxhx.library.grade.read.newx.view.ScoreView}
 */
@IntDef({
        ScorePageState.NO_NEXT,
        ScorePageState.NO_PREV,
        ScorePageState.ANOTHER_NEXT,
        ScorePageState.ANOTHER_PREV,
        ScorePageState.NEXT,
        ScorePageState.PREV,
        ScorePageState.NO_STATE,
})
@Retention(RetentionPolicy.SOURCE)
public @interface ScorePageState {
    /**
     * 最后一道题{@link OldScoreView#onScoreNextNetNoMore()}
     * 最后一道题{@link com.zxhx.library.grade.read.newx.view.ScoreView#onScoreNextNetNoMore()}
     */
    int NO_NEXT = 0;
    /**
     * 第一道题{@link OldScoreView#onScorePrevNetNoMore()}
     * 第一道题{@link com.zxhx.library.grade.read.newx.view.ScoreView#onScorePrevNetNoMore()}
     */
    int NO_PREV = 1;
    /**
     * 下一套题{@link OldScoreView#onScoreNextNetAnother(ScoreNextEntity.NextBean)}
     * 下一套题{@link ScoreView#onScoreNextNetAnother()}
     */
    int ANOTHER_NEXT = 2;
    /**
     * 上一套题{@link OldScoreView#onScorePrevNetAnother(ScorePrevEntity.PrevBean)}
     * 上一套题{@link ScoreView#onScorePrevNetAnother()}
     */
    int ANOTHER_PREV = 3;
    /**
     * 下一页数据@{@link OldScoreView#onScoreNextNetSuccess(ScoreZipEntity)}
     * 下一页数据@{@link ScoreView#onScoreFirstNetSuccess(ScoreZipV2Entity)}
     */
    int NEXT = 4;
    /**
     * 上一页数据{@link OldScoreView#onScorePrevNetSuccess(ScoreZipEntity)}
     * 上一页数据{@link ScoreView#onScorePrevNetSuccess(ScoreZipV2Entity)}
     */
    int PREV = 5;
    /**
     * 状态赋值错误
     */
    int NO_STATE = -1;
}
