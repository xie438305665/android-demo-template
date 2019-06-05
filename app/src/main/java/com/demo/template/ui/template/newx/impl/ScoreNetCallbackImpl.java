package com.demo.template.ui.template.newx.impl;

import com.android.library.bridge.core.base.IView;
import com.android.library.bridge.core.listener.SimpleNetZipSuccessListener;
import com.android.library.bridge.key.Booleans;
import com.demo.template.entity.ScoreZipV2Entity;
import com.demo.template.annotation.GradeScoreType;
import com.demo.template.ui.template.newx.view.ScoreView;

/**
 * @author y
 * @create 2019/3/22
 */
public class ScoreNetCallbackImpl extends SimpleNetZipSuccessListener<ScoreZipV2Entity> {

    private ScoreView scoreView;
    @GradeScoreType
    private int gradeScoreNetType;
    @Booleans
    private int hasPrevLoading;

    ScoreNetCallbackImpl(IView mView, @GradeScoreType int gradeScoreNetType, @Booleans int hasPreLoading) {
        super(mView);
        this.hasPrevLoading = hasPreLoading;
        this.scoreView = (ScoreView) mView;
        this.gradeScoreNetType = gradeScoreNetType;
    }

    @Override
    public void onNetWorkStart() {
        if (hasPrevLoading == Booleans.TRUE) {
            return;
        }
        super.onNetWorkStart();
    }

    @Override
    public void onNetWorkComplete() {
        if (hasPrevLoading == Booleans.TRUE) {
            return;
        }
        super.onNetWorkComplete();
    }

    @Override
    public void onNetWorkError(Throwable e) {
        if (hasPrevLoading == Booleans.TRUE) {
            return;
        }
        super.onNetWorkError(e);
        if (gradeScoreNetType == GradeScoreType.UN) {
            scoreView.onShowUnError();
        } else if (gradeScoreNetType == GradeScoreType.FIRST) {
            scoreView.onShowFirstError();
        }
    }

    @Override
    public void onNetWorkSuccess(ScoreZipV2Entity data) {
        if (scoreView == null || data == null) {
            return;
        }
        switch (gradeScoreNetType) {
            case GradeScoreType.FIRST:
                scoreView.onScoreFirstNetSuccess(data);
                break;
            case GradeScoreType.PROBLEM:
                scoreView.onScoreProblemNetSuccess(data);
                break;
            case GradeScoreType.UN:
                scoreView.onScoreUnNetSuccess(data);
                break;
            case GradeScoreType.NEXT:
                scoreView.onScoreNextNetSuccess(data);
                break;
            case GradeScoreType.PREV:
                scoreView.onScorePrevNetSuccess(data);
                break;
            case GradeScoreType.UN_NEXT:
                scoreView.onScoreUnNextNetSuccess(data);
                break;
            case GradeScoreType.PROBLEM_PREV:
                scoreView.onScoreProblemPrevNetAnother(data.getTaskList().getList().get(0));
                break;
            case GradeScoreType.PROBLEM_NEXT:
                scoreView.onScoreProblemNextAnother(data.getTaskList().getList().get(0));
                break;
            case GradeScoreType.ARBITRATION_NEXT:
                scoreView.onArbitrationScoreNextNetSuccess(data);
                break;
            case GradeScoreType.ARBITRATION_PREV:
                scoreView.onArbitrationScorePrevNetSuccess(data);
                break;
            case GradeScoreType.ARBITRATION:
                scoreView.onArbitrationScoreFirstNetSuccess(data);
                break;
        }
    }
}
