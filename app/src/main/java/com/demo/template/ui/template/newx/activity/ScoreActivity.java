package com.demo.template.ui.template.newx.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.library.bridge.RoutePath;
import com.android.library.bridge.annotation.QuestionType;
import com.android.library.bridge.util.NumberUtils;
import com.android.library.bridge.util.UIUtils;
import com.android.library.net.entity.template.PairsMyProgressEntity;
import com.android.library.net.entity.template.ScoreParameterEntity;
import com.demo.template.R;
import com.demo.template.annotation.ReadUIMode;
import com.demo.template.listener.action.DefaultLayoutAction;
import com.demo.template.listener.action.DefaultV2LayoutAction;
import com.demo.template.listener.read.OnScoreListener;
import com.demo.template.listener.read.OnScoreOwnListener;
import com.demo.template.entity.ScoreMultiEntity;
import com.demo.template.ui.template.newx.adapter.ScoreAdapter;
import com.demo.template.ui.template.newx.impl.ScorePresenter;
import com.demo.template.utils.HtmlUtils;
import com.demo.template.utils.ScoreUtils;

/**
 * @author xcl
 * 解答题打分
 */
@Route(path = RoutePath.GRADE_V2_SCORE)
public class ScoreActivity extends ScoreNetActivity implements OnScoreOwnListener, OnScoreListener {

    @Override
    protected void initCreate(@Nullable Bundle savedInstanceState) {
        super.initCreate(savedInstanceState);
        onChangedFragment(false);
    }

    @Override
    protected ScorePresenter initPresenter() {
        return null;
    }

    @NonNull
    @Override
    protected DefaultLayoutAction getDefaultLayoutAction() {
        if (UIUtils.checkNull(getScoreAdapter())) {
            setScoreAdapter(new ScoreAdapter(getSupportFragmentManager()));
        }
        return new DefaultV2LayoutAction(this, getScoreAdapter());
    }

    @Override
    public void replaceFragment(@NonNull ScoreParameterEntity entity, boolean hasNext) {
        if (UIUtils.checkNull(getParameter())) {
            return;
        }
        getParameter().setTopicId(entity.getTopicId());
        getParameter().setExamGroupId(entity.getExamGroupId());
        getParameter().setStudentId(entity.getStudentId());
        getParameter().setScoreType(entity.getScoreType());
        resetData();
        boolean landscape = false;
        if (getParameter().getScoreType() == QuestionType.FILL && UIUtils.isLandscape(this)) {
            landscape = true;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        }
        //为什么需要延迟
        viewPager.postDelayed(() -> {
            onChangedViewState(ReadUIMode.REPLACE);
            onChangedFragment(hasNext);
        }, landscape ? 300 : 0);
    }

    /**
     * 切换Fragment
     *
     * @param hasNext 下一题/上一题
     */
    @Override
    public void onChangedFragment(boolean hasNext) {
        int scoreType = getScoreType();
        if (scoreType == QuestionType.FILL) {
            setHasLand(false);
        }
        onClearAnswerEditTextFocus();
        //为什么需要先删除
        if (UIUtils.checkNotNull(getScoreAdapter())) {
            getScoreAdapter().onRemoveFragment();
        }
        setShowUnScore(false);
        onChangeDrawerMode(scoreType);
        if (viewPager.getCurrentItem() == 0) {
            //如果currentItem == 0   为什么不是直接设置0   而是最大值/2
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
        if (isProblem() || UIUtils.checkNull(getCurrentProgressItem())) {
            return;
        }
        PairsMyProgressEntity currentProgressItem = getCurrentProgressItem();
        scoreHeader.updateV2PercentageAndSchedule(currentProgressItem);
        if (getScoreType() == QuestionType.ANSWER) {
            answerLandToolbar.updateV2PercentageAndSchedule(currentProgressItem);
        }
        if (isArbitration()) {
            return;
        }
        scoreHeader.hasNewMark(hasNewMark());
        answerLandToolbar.hasNewMark(hasNewMark());
    }

    @Override
    public void onRefreshAnswerName() {
        if (isArbitration()) {
            if (UIUtils.checkNull(getAnswerEntity())) {
                return;
            }
            String nameHtml = HtmlUtils.getNameHtml(getAnswerEntity().getTeacherMarkingScores());
            answerLandToolbar.updateName(Html.fromHtml(nameHtml));
            scoreHeader.updateArbitrationName(Html.fromHtml(nameHtml));
            return;
        }
        if (UIUtils.checkNull(getAnswerEntity())) {
            return;
        }
        if (!isMixing() || isProblem()) {
            answerLandToolbar.updateName(getAnswerEntity().getStudentName());
            scoreHeader.updateName(getAnswerEntity().getStudentName());
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
        answerLandToolbar.updateAnswerMaxScore(NumberUtils.parseDouble(getMaxTopicScore()));
        scoreHeader.updateAnswerMaxScore(NumberUtils.parseDouble(getMaxTopicScore()));
    }

    @Override
    public void onRefreshTitle() {
        if (isProblem()) {
            if (UIUtils.checkNull(getAnswerEntity())) {
                return;
            }
            toolbar.setTitle(String.format(UIUtils.getString(R.string.grade_score_title), getAnswerEntity().getTopicNo()));
            if (getScoreType() == QuestionType.ANSWER) {
                answerLandToolbar.setTitle(getAnswerEntity().getTopicNo());
            }
            return;
        }
        if (UIUtils.checkNull(getCurrentProgressItem())) {
            return;
        }
        toolbar.setTitle(String.format(UIUtils.getString(R.string.grade_score_title), getCurrentProgressItem().getTopicNoText()));
        if (getScoreType() == QuestionType.ANSWER) {
            answerLandToolbar.setTitle(getCurrentProgressItem().getTopicNoText());
        }
    }

    @Override
    public void onRefreshKeyboard() {
        String topicId = getTopicId();
        if (TextUtils.isEmpty(topicId)) {
            return;
        }
        keyboard.updateV2Keyboard(getScoreType(), topicId, isProblem());
        if (getScoreType() == QuestionType.ANSWER) {
            answerLandKeyboard.updateV2Keyboard(topicId);
        }
    }

    /**
     * 失去焦点
     */
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
