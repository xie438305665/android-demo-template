package com.demo.template.ui.template.oldx.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.library.bridge.annotation.QuestionType;
import com.android.library.bridge.core.StatusFragment;
import com.android.library.bridge.util.UIUtils;
import com.android.library.net.entity.template.ScoreParameterEntity;
import com.demo.template.R;
import com.demo.template.ui.template.oldx.activity.OldScoreActivity;

@Deprecated
public class OldScoreBridgeFragment extends StatusFragment {

    private OldBaseScoreFragment bride;

    @Nullable
    public OldBaseScoreFragment bride() {
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
        OldScoreActivity scoreActivity = (OldScoreActivity) mActivity;
        ScoreParameterEntity parameter = scoreActivity.getParameter();
        if (UIUtils.checkNull(parameter)) {
            return;
        }
        if (scoreActivity.getScoreType() == QuestionType.ANSWER) {
            bride = OldAnswerScoreFragment.newInstance(parameter);
        } else if (scoreActivity.getScoreType() == QuestionType.FILL) {
            bride = OldFillScoreFragment.newInstance(parameter);
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

