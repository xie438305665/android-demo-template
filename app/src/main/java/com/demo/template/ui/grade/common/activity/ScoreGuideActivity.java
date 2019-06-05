package com.demo.template.ui.grade.common.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.android.library.bridge.annotation.QuestionType;
import com.android.library.bridge.core.base.IPresenter;
import com.android.library.bridge.util.SpUtils;
import com.android.library.bridge.util.UIUtils;
import com.demo.template.R;
import com.demo.template.annotation.GuideType;
import com.demo.template.listener.read.OnScoreGuideOwnListener;
import com.demo.template.widget.ScoreGuideView;

/**
 * @author y
 * @create 2019/4/4
 */
public abstract class ScoreGuideActivity<P extends IPresenter> extends BaseInitScoreActivity<P> implements OnScoreGuideOwnListener {

    private ScoreGuideView scoreGuideView;
    private boolean showFillGuide = false;
    private boolean showAnswerGuide = false;

    @Override
    protected void initCreate(@Nullable Bundle savedInstanceState) {
        super.initCreate(savedInstanceState);
        scoreGuideView = newInstanceGuideView();
        if (UIUtils.checkNull(scoreGuideView)) {
            return;
        }
        mStatusLayout.addView(scoreGuideView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Nullable
    @Override
    public ScoreGuideView newInstanceGuideView() {
        if (isProblem()) {
            return null;
        }
        String key = getScoreType() == QuestionType.FILL ? GuideType.FILL : GuideType.ANSWER;
        int guideNum = SpUtils.getInt(key, 0);
        if (guideNum >= 2) {
            return null;
        }
        if (scoreGuideView == null) {
            scoreGuideView = new ScoreGuideView(this, getScoreType(), () -> SpUtils.setInt(key, guideNum + 1));
            scoreGuideView.setBackgroundColor(UIUtils.getColor(R.color.colorLoadingBg));
        }
        if (getScoreType() == QuestionType.FILL) {
            showFillGuide = true;
        } else {
            showAnswerGuide = true;
        }
        return scoreGuideView;
    }

    @Override
    public void showGuideView(@QuestionType int scoreType) {
        scoreGuideView.setVisibility(View.VISIBLE);
        scoreGuideView.click(scoreType);
        if (scoreType == QuestionType.FILL) {
            showFillGuide = true;
        } else {
            showAnswerGuide = true;
        }
    }

    @Override
    public boolean hasShowGuideView(@QuestionType int scoreType) {
        if (scoreGuideView == null) {
            return false;
        }
        if (scoreType == QuestionType.FILL && showFillGuide) {
            return false;
        }
        if (scoreType == QuestionType.ANSWER && showAnswerGuide) {
            return false;
        }
        return !showAnswerGuide || !showFillGuide;
    }
}
