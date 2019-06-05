package com.demo.template.ui.read.oldx.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.library.bridge.RoutePath;
import com.android.library.bridge.annotation.QuestionType;
import com.android.library.bridge.util.NumberUtils;
import com.android.library.bridge.util.UIUtils;
import com.android.library.net.entity.template.ScoreParameterEntity;
import com.demo.template.R;
import com.demo.template.annotation.ReadUIMode;
import com.demo.template.listener.action.DefaultOldLayoutAction;
import com.demo.template.listener.read.OnScoreListener;
import com.demo.template.listener.read.OnScoreOwnListener;
import com.demo.template.entity.ScoreMultiEntity;
import com.demo.template.ui.read.oldx.adapter.OldScoreAdapter;
import com.demo.template.ui.read.oldx.impl.OldScorePresenter;
import com.demo.template.utils.ScoreUtils;

/**
 * 解答题打分
 */
@Deprecated
@Route(path = RoutePath.GRADE_V1_SCORE)
public class OldScoreActivity extends OldScoreNetActivity implements OnScoreOwnListener, OnScoreListener {

    @Override
    protected void initCreate(@Nullable Bundle savedInstanceState) {
        super.initCreate(savedInstanceState);
        onChangedFragment(false);
    }

    @Override
    protected OldScorePresenter initPresenter() {
        return null;
    }

    @NonNull
    @Override
    protected DefaultOldLayoutAction getDefaultLayoutAction() {
        if (getScoreAdapter() == null) {
            setScoreAdapter(new OldScoreAdapter(getSupportFragmentManager()));
        }
        return new DefaultOldLayoutAction(this, getScoreAdapter());
    }

    @Override
    public void replaceFragment(@NonNull ScoreParameterEntity entity, boolean hasNext) {
        if (UIUtils.checkNull(getParameter())) {
            return;
        }
        getParameter().setMarkingGroupId(entity.getMarkingGroupId());
        getParameter().setExamGroupId(entity.getExamGroupId());
        getParameter().setStudentId(entity.getStudentId());
        getParameter().setScoreType(entity.getScoreType());
        resetData();
        boolean landscape = false;
        if (getScoreType() == QuestionType.FILL && UIUtils.isLandscape(this)) {
            landscape = true;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        }
        viewPager.postDelayed(() -> {
            onChangedViewState(ReadUIMode.REPLACE);
            onChangedFragment(hasNext);
        }, landscape ? 300 : 0);
    }

    @Override
    public void onChangedFragment(boolean hasNext) {
        int scoreType = getScoreType();
        if (scoreType == QuestionType.FILL) {
            setHasLand(false);
        }
        onClearAnswerEditTextFocus();
        if (UIUtils.checkNotNull(getScoreAdapter())) {
            getScoreAdapter().onRemoveFragment();
        }
        setShowUnScore(false);
        onChangeDrawerMode(scoreType);
        if (viewPager.getCurrentItem() == 0) {
            viewPager.setCurrentItem(Integer.MAX_VALUE / 2);
        } else if (hasNext) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
        }
        if (hasShowGuideView(getScoreType())) {
            showGuideView(getScoreType());
        }
    }

    @NonNull
    @Override
    public String getAnswerFraction() {
        return UIUtils.isLandscape(this) ? answerLandToolbar.getLandFraction() : scoreHeader.getAnswerFraction();
    }

    @Override
    public void onRefreshPercentageAndSchedule() {
        if (isProblem() || UIUtils.checkNull(getGQEntity())) {
            return;
        }
        scoreHeader.updatePercentageAndSchedule(getGQEntity());
        if (getScoreType() == QuestionType.ANSWER) {
            answerLandToolbar.updatePercentageAndSchedule(getGQEntity());
        }
    }

    @Override
    public void onRefreshAnswerName() {
        if (UIUtils.checkNull(getAnswerEntity())) {
            return;
        }
        if (!isMixing() || isProblem()) {
            answerLandToolbar.updateName(getAnswerEntity().getStudentPaperTopic().getStudentName());
            scoreHeader.updateName(getAnswerEntity().getStudentPaperTopic().getStudentName());
        }
    }

    @Override
    public void onRefreshAnswerFraction() {
        if (UIUtils.checkNull(getAnswerEntity())) {
            return;
        }
        answerLandToolbar.updateAnswerFraction(ScoreUtils.getCurrentFraction(getAnswerEntity()));
        scoreHeader.updateAnswerFraction(ScoreUtils.getCurrentFraction(getAnswerEntity()));
    }

    @Override
    public void onRefreshAnswerMaxScore() {
        if (UIUtils.checkNull(getGQEntity())) {
            return;
        }
        answerLandToolbar.updateAnswerMaxScore(NumberUtils.parseDouble(getGQEntity().getScore()));
        scoreHeader.updateAnswerMaxScore(NumberUtils.parseDouble(getGQEntity().getScore()));
    }

    @Override
    public void onRefreshTitle() {
        if (UIUtils.checkNull(getGQEntity())) {
            return;
        }
        toolbar.setTitle(String.format(UIUtils.getString(R.string.grade_score_title), getGQEntity().getIndex()));
        if (getScoreType() == QuestionType.ANSWER) {
            answerLandToolbar.setTitle(getGQEntity().getIndex());
        }
    }

    @Override
    public void onRefreshKeyboard() {
        String markingGroupId = getMarkingGroupId();
        if (TextUtils.isEmpty(markingGroupId)) {
            return;
        }
        keyboard.updateKeyboard(getScoreType(), markingGroupId, isProblem());
        if (getScoreType() == QuestionType.ANSWER) {
            answerLandKeyboard.updateKeyboard(markingGroupId);
        }
    }

    @Override
    public void onClearAnswerEditTextFocus() {
        UIUtils.forceOffKeyboard(this);
        scoreHeader.onClearFocusAnswerFraction();
        answerLandToolbar.clearFocus();
    }

    @Override
    public void onVisibilityChanged(boolean flag) {
        if (UIUtils.checkNull(getScoreAdapter()) || getScoreAdapter().getAnswerFragment() == null || flag) {
            return;
        }
        String fraction = getAnswerFraction();
        if (!TextUtils.isEmpty(fraction)) {
            return;
        }
        getScoreAdapter().getAnswerFragment().onFractionKeyboardChanged(null);
        onClearAnswerEditTextFocus();
    }

    @Override
    public void unAnswerNetWork() {
        if (UIUtils.checkNull(getScoreAdapter())) {
            return;
        }
        getScoreAdapter().onShowUnAnswerScore();
    }

    @Override
    public void onDrawerItemClick(@NonNull View view, int position, @NonNull ScoreMultiEntity info) {
        if (UIUtils.checkNull(getScoreAdapter())) {
            return;
        }
        if (getScoreType() == QuestionType.FILL) {
            getScoreAdapter().onFillScoreDrawerItemClick(position);
        } else if (getScoreType() == QuestionType.ANSWER) {
            getScoreAdapter().onAnswerScoreDrawerItemClick(position);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == RESULT_KEY_BOARD) {
            onRefreshKeyboard();
        }
    }
}
