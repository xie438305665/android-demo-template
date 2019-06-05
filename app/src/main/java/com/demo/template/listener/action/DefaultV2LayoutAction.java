package com.demo.template.listener.action;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.android.library.bridge.util.UIUtils;
import com.demo.template.R;
import com.demo.template.ui.template.common.dialog.LookAnswerDialog;
import com.demo.template.ui.template.newx.activity.ScoreActivity;
import com.demo.template.ui.template.newx.adapter.ScoreAdapter;
import com.status.layout.Status;

/**
 * @author y
 * @create 2019/4/3
 */
public class DefaultV2LayoutAction extends DefaultLayoutAction {

    private ScoreActivity scoreActivity;
    private ScoreAdapter scoreAdapter;

    public DefaultV2LayoutAction(@NonNull ScoreActivity scoreActivity, @NonNull ScoreAdapter scoreAdapter) {
        this.scoreActivity = scoreActivity;
        this.scoreAdapter = scoreAdapter;
    }

    @Status
    @Override
    public String getCurrentStatus() {
        if (UIUtils.checkNull(scoreAdapter) || UIUtils.checkNull(scoreAdapter.getCurrentFragment())) {
            return Status.NORMAL;
        }
        return scoreAdapter.getCurrentFragment().getCurrentStatus();
    }

    @Override
    public void onAnswerLandSubmit(@NonNull String fraction) {
        scoreAdapter.onAnswerLandSubmit(fraction);
    }

    @Override
    public void onAnswerLandToolbarOpenDrawer() {
        scoreActivity.onOpenDrawer();
    }

    @Override
    public void onAnswerLandToolbarIssues() {
        scoreAdapter.answerIssues();
    }

    @Override
    public void onAnswerLandToolbarFinish() {
        scoreActivity.onRefreshPrevUI();
        scoreActivity.finish();
    }

    @Override
    public void onAnswerLandToolbarKeyboardChanged(@NonNull String landFraction) {
        scoreAdapter.onAnswerKeyboardChanged(landFraction);
    }

    @Override
    public void onScoreHeaderAllFalse() {
        scoreAdapter.fillAllFalse();
    }

    @Override
    public void onScoreHeaderAllTrue() {
        scoreAdapter.fillAllTrue();
    }

    @Override
    public void onScoreHeaderAnswer() {
        if (UIUtils.checkNull(scoreActivity.getTopAnswerEntity())) {
            return;
        }
        String currentAnswer = scoreActivity.getTopAnswerEntity().getAnswer();
        if (TextUtils.isEmpty(currentAnswer)) {
            return;
        }
        LookAnswerDialog.shows(scoreActivity.getSupportFragmentManager(), currentAnswer);
    }

    @Override
    public void onScoreFractionKeyboardChanged(@NonNull String fraction) {
        scoreAdapter.onAnswerKeyboardChanged(fraction);
    }

    @Override
    public void onScoreToolbarFinish() {
        scoreActivity.onRefreshPrevUI();
        scoreActivity.finish();
    }

    @Override
    public void onScoreToolbarOpenDrawer() {
        scoreActivity.onOpenDrawer();
    }

    @Override
    public void onAnswerPortIssues() {
        scoreAdapter.answerIssues();
    }

    @Override
    public void onAnswerPortMoreFraction() {
        scoreAdapter.showAnswerFractionMore();
    }

    @Override
    public void onAnswerPortFractionSubmit(String fraction) {
        scoreAdapter.onAnswerScoreSubmit(fraction);
    }

    @Override
    public void onFillKeyboardRight() {
        if (UIUtils.checkNull(scoreAdapter.getFillFragment())) {
            return;
        }
        scoreAdapter.fillSubmit(String.valueOf(scoreActivity.getMaxTopicScore()));
    }

    @Override
    public void onFillKeyboardWrong() {
        scoreAdapter.fillSubmit(String.valueOf(0));
    }

    @Override
    public void onFillKeyboardUnKnown() {
        scoreAdapter.fillSubmit(UIUtils.getString(R.string.grade_unknown));
    }

    @Override
    public void onFillKeyboardSubmit(@NonNull String fraction) {
        scoreAdapter.fillSubmit(fraction);
    }
}
