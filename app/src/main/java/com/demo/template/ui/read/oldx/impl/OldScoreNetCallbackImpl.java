package com.demo.template.ui.read.oldx.impl;

import com.android.library.bridge.core.base.IView;
import com.android.library.bridge.core.listener.SimpleNetZipSuccessListener;
import com.android.library.bridge.key.Booleans;
import com.demo.template.entity.ScoreZipEntity;
import com.demo.template.annotation.GradeScoreType;
import com.demo.template.annotation.ScorePageState;
import com.demo.template.ui.read.oldx.view.OldScoreView;

/**
 * @author y
 * @create 2019/3/22
 */
@Deprecated
public class OldScoreNetCallbackImpl extends SimpleNetZipSuccessListener<ScoreZipEntity> {

    private OldScoreView scoreView;
    @GradeScoreType
    private int gradeScoreNetType;
    @Booleans
    private int hasPrevLoading;

    OldScoreNetCallbackImpl(IView mView, @GradeScoreType int gradeScoreNetType, @Booleans int hasPreLoading) {
        super(mView);
        this.hasPrevLoading = hasPreLoading;
        this.scoreView = (OldScoreView) mView;
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
    public void onNetWorkSuccess(ScoreZipEntity data) {
        if (scoreView == null || data == null) {
            return;
        }
        @ScorePageState int pageSate = data.getPageSate();
        switch (gradeScoreNetType) {
            case GradeScoreType.FIRST:
                scoreView.onScoreFirstNetSuccess(data);
                break;
            case GradeScoreType.PROBLEM:
                scoreView.onScoreProblemNetSuccess(data);
                break;
            case GradeScoreType.UN:
                if (data.getStudentFirst().isEmpty()) {
                    scoreView.onShowUnEmpty();
                } else {
                    scoreView.onScoreUnNetSuccess(data);
                }
                break;
            case GradeScoreType.NEXT:
                if (pageSate == ScorePageState.NEXT) {
                    scoreView.onScoreNextNetSuccess(data);
                } else if (pageSate == ScorePageState.ANOTHER_NEXT) {
                    scoreView.onScoreNextNetAnother(data.getStudentNext().getNext());
                } else if (pageSate == ScorePageState.NO_NEXT) {
                    scoreView.onScoreNextNetNoMore();
                }
                break;
            case GradeScoreType.PREV:
                if (pageSate == ScorePageState.PREV) {
                    scoreView.onScorePrevNetSuccess(data);
                } else if (pageSate == ScorePageState.ANOTHER_PREV) {
                    scoreView.onScorePrevNetAnother(data.getStudentPrev().getPrev());
                } else if (pageSate == ScorePageState.NO_PREV) {
                    scoreView.onScorePrevNetNoMore();
                }
                break;
            case GradeScoreType.UN_NEXT:
                if (pageSate == ScorePageState.NEXT) {
                    scoreView.onScoreUnNextNetSuccess(data);
                } else if (pageSate == ScorePageState.ANOTHER_NEXT) {
                    scoreView.onScoreUnNextNetAnother(data.getStudentNext().getNext());
                } else if (pageSate == ScorePageState.NO_NEXT) {
                    scoreView.onScoreUnNextNetNoMore();
                }
                break;
            case GradeScoreType.PROBLEM_PREV:
                if (pageSate == ScorePageState.ANOTHER_PREV) {
                    scoreView.onScoreProblemPrevNetAnother(data.getStudentFirst().get(0));
                } else if (pageSate == ScorePageState.NO_PREV) {
                    scoreView.onScoreProblemPrevNetNoMore();
                }
                break;
            case GradeScoreType.PROBLEM_NEXT:
                if (pageSate == ScorePageState.ANOTHER_NEXT) {
                    scoreView.onScoreProblemNextAnother(data.getStudentFirst().get(0));
                } else if (pageSate == ScorePageState.NO_NEXT) {
                    scoreView.onScoreProblemNextNetNoMore();
                }
                break;
        }
    }
}
