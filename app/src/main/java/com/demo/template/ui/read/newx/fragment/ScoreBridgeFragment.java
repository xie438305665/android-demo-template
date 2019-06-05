package com.demo.template.ui.read.newx.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.library.bridge.annotation.QuestionType;
import com.android.library.bridge.core.StatusFragment;
import com.android.library.bridge.util.UIUtils;
import com.android.library.net.entity.template.ScoreParameterEntity;
import com.demo.template.R;
import com.demo.template.ui.read.newx.activity.ScoreActivity;

public class ScoreBridgeFragment extends StatusFragment {

    private BaseScoreFragment bride;

    @Nullable
    public BaseScoreFragment bride() {
        return bride;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.grade_layout_score_bridge;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        onActivityCreated(null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!getUserVisibleHint() || UIUtils.checkNull(mActivity) || UIUtils.checkNotNull(bride)) {
            return;
        }
        ScoreActivity scoreActivity = (ScoreActivity) mActivity;
        ScoreParameterEntity parameter = scoreActivity.getParameter();
        if (UIUtils.checkNull(parameter)) {
            return;
        }
        if (scoreActivity.getScoreType() == QuestionType.ANSWER) {
            bride = AnswerScoreFragment.newInstance(parameter);
        } else if (scoreActivity.getScoreType() == QuestionType.FILL) {
            bride = FillScoreFragment.newInstance(parameter);
        }
        getChildFragmentManager().beginTransaction().add(R.id.score_bridge_root_view, bride, bride.getClass().getSimpleName()).commitAllowingStateLoss();
    }

    public void onDestroyChildFragment() {
        if (bride != null) {
            getChildFragmentManager().beginTransaction().remove(bride).commitAllowingStateLoss();
            bride = null;
        }
    }
}

