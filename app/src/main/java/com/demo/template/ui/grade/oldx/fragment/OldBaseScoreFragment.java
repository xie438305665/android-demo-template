package com.demo.template.ui.grade.oldx.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.library.bridge.annotation.QuestionType;
import com.android.library.bridge.annotation.VersionType;
import com.android.library.bridge.core.MVPFragment;
import com.android.library.bridge.key.Booleans;
import com.android.library.bridge.util.UIUtils;
import com.android.library.db.GreenDaoManager;
import com.android.library.net.body.read.SubmitScoreBody;
import com.android.library.net.entity.template.ScoreEntity;
import com.android.library.net.entity.template.ScoreNextEntity;
import com.android.library.net.entity.template.ScoreParameterEntity;
import com.android.library.net.entity.template.ScorePrevEntity;
import com.demo.template.entity.ScoreZipEntity;
import com.android.library.widget.custom.CustomViewPager;
import com.demo.template.R;
import com.demo.template.annotation.BundleKey;
import com.demo.template.annotation.GradeScoreType;
import com.demo.template.annotation.TouchMode;
import com.demo.template.factory.ScoreKeyboardFactory;
import com.demo.template.listener.action.SimpleOnPageChangeAction;
import com.demo.template.listener.read.OldOnScoreChildOwnListener;
import com.demo.template.ui.grade.oldx.activity.OldScoreActivity;
import com.demo.template.ui.grade.oldx.impl.OldScoreImpl;
import com.demo.template.ui.grade.oldx.impl.OldScorePresenterImpl;
import com.demo.template.ui.grade.oldx.view.OldScoreView;

import java.util.ArrayList;

import butterknife.BindView;


/**
 * @author y
 */
@Deprecated
public abstract class OldBaseScoreFragment extends MVPFragment<OldScorePresenterImpl, ScoreZipEntity> implements SimpleOnPageChangeAction, OldScoreView, OldOnScoreChildOwnListener {

    @BindView(R.id.score_child_viewpager)
    CustomViewPager viewPager;

    private OldScoreActivity scoreActivity;
    @GradeScoreType
    private int scoreNetType;
    private boolean problem;
    private boolean mixing;
    private boolean first = true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        scoreActivity = (OldScoreActivity) mActivity;
    }

    @Override
    protected void initCreate(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ScoreParameterEntity parameterEntity = bundle.getParcelable(BundleKey.GRADE_SCORE_ENTITY);
        if (UIUtils.checkNull(parameterEntity)) {
            parameterEntity = ScoreParameterEntity.newInstance();
        }
        problem = parameterEntity.isProblem();
        mixing = parameterEntity.isMixing();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewPager.addOnPageChangeListener(this);
        viewPager.setAdapter(initPageAdapter());
        onStatusRetry();
    }

    @Override
    protected void onStatusRetry() {
        if (problem) {
            netRequest(GradeScoreType.PROBLEM, true);
        } else if (UIUtils.checkNotNull(getScoreActivity()) && getScoreActivity().isShowUnScore()) {
            netRequest(GradeScoreType.UN, true);
        } else {
            netRequest(GradeScoreType.FIRST, true);
        }
    }

    @Override
    protected OldScorePresenterImpl initPresenter() {
        return new OldScoreImpl(this);
    }

    public void onShowUnAnswerScore() {
        netRequest(UIUtils.checkNotNull(getScoreActivity()) && getScoreActivity().isShowUnScore() ? GradeScoreType.UN : GradeScoreType.FIRST, true);
    }

    @Override
    public void onShowFirstError() {
        if (UIUtils.checkNotNull(getScoreActivity()) && !isFirst()) {
            getScoreActivity().setShowUnScore(true);
        }
    }

    @Override
    public void onShowUnError() {
        if (UIUtils.checkNotNull(getScoreActivity())) {
            getScoreActivity().setShowUnScore(!getScoreActivity().isShowUnScore());
        }
    }

    @Override
    public void onShowUnEmpty() {
        UIUtils.show(R.string.grade_score_un_empty);
        if (UIUtils.checkNotNull(getScoreActivity())) {
            getScoreActivity().setShowUnScore(false);
        }
    }

    public void onPageSelected(int position) {
    }

    @Override
    public void onSubmitSuccess(@NonNull ScoreZipEntity entity, @NonNull String fraction, @TouchMode int type) {
//        UIUtils.show(R.string.grade_submit_success);\
        if (UIUtils.checkNotNull(getScoreActivity())) {
            if (!isProblem()) {
                getScoreActivity().onRefreshGroupQuota(entity.getGroupQuota());
            }
            getScoreActivity().onRefreshPercentageAndSchedule();
        }
    }

    @Override
    public void onScoreFirstNetSuccess(@NonNull ScoreZipEntity entity) {
        if (UIUtils.checkNotNull(getScoreActivity())) {
            getScoreActivity().onRefreshGroupQuota(entity.getGroupQuota());
            getScoreActivity().onRefreshTopicAnswer(entity.getTopicAnswerEntity());
            getScoreActivity().onRefreshScoreList(entity.getStudentFirst());
        }
        if (isFirst()) {
            viewPager.setCurrentItem(Integer.MAX_VALUE / 2);
            setFirst(false);
            if (UIUtils.checkNull(GreenDaoManager.queryKeyboardV1(getScoreActivity().getMarkingGroupId()))) {
                ScoreKeyboardFactory.initScoreKeyboardV1(getScoreActivity().getMarkingGroupId(), getScoreActivity().getMaxTopicScore());
            }
            onChangedParentUI();
        } else {
            onChangedUI();
        }
    }

    @Override
    public void onScoreUnNetSuccess(@NonNull ScoreZipEntity entity) {
        if (UIUtils.checkNotNull(getScoreActivity())) {
            getScoreActivity().onRefreshScoreList(entity.getStudentFirst());
            getScoreActivity().setShowUnScore(true);
        }
        onChangedUI();
    }

    @Override
    public void onScoreProblemNetSuccess(@NonNull ScoreZipEntity zipEntity) {
        if (!UIUtils.isEmpty(zipEntity.getStudentFirst())) {
            for (int i = 0; i < zipEntity.getStudentFirst().size(); i++) {
                if (i == 0) {
                    zipEntity.getStudentFirst().get(i).setSelect(true);
                }
            }
        }
        onScoreFirstNetSuccess(zipEntity);
    }

    @Override
    public void onScoreNextNetSuccess(@NonNull ScoreZipEntity zipEntity) {
        if (UIUtils.checkNotNull(getScoreActivity())) {
            getScoreActivity().onRefreshScoreList(zipEntity.getStudentNext().getList());
        }
        scoreViewPagerNext();
    }

    @Override
    public void onScorePrevNetSuccess(@NonNull ScoreZipEntity zipEntity) {
        if (UIUtils.checkNotNull(getScoreActivity())) {
            getScoreActivity().onRefreshScoreList(zipEntity.getStudentPrev().getList());
        }
        scoreViewPagerPrev();
    }

    @Override
    public void onScoreUnNextNetSuccess(@NonNull ScoreZipEntity zipEntity) {
        if (UIUtils.checkNotNull(getScoreActivity())) {
            getScoreActivity().onRefreshScoreList(zipEntity.getStudentNext().getList());
        }
        scoreViewPagerNext();
    }

    @Override
    public void onScoreNextNetAnother(@NonNull ScoreNextEntity.NextBean next) {
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        UIUtils.show(R.string.grade_next_score_success);
        getScoreActivity().replaceFragment(ScoreParameterEntity.newReplaceInstanceV1(getScoreActivity().getExamGroupId(), next.getMarkingGroupId(), getScoreActivity().getNextStudentId(), next.getTopicType() == QuestionType.FILL ? QuestionType.FILL : QuestionType.ANSWER, VersionType.V1), true);
    }

    @Override
    public void onScorePrevNetAnother(@NonNull ScorePrevEntity.PrevBean prev) {
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        UIUtils.show(R.string.grade_prev_score_success);
        getScoreActivity().replaceFragment(ScoreParameterEntity.newReplaceInstanceV1(getScoreActivity().getExamGroupId(), prev.getMarkingGroupId(), getScoreActivity().getPrevStudentId(), prev.getTopicType() == QuestionType.FILL ? QuestionType.FILL : QuestionType.ANSWER, VersionType.V1), false);
    }

    @Override
    public void onScoreUnNextNetAnother(@NonNull ScoreNextEntity.NextBean next) {
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        UIUtils.show(R.string.grade_next_score_success);
        getScoreActivity().replaceFragment(ScoreParameterEntity.newReplaceInstanceV1(getScoreActivity().getExamGroupId(), next.getMarkingGroupId(), getScoreActivity().getNextStudentId(), next.getTopicType() == QuestionType.FILL ? QuestionType.FILL : QuestionType.ANSWER, VersionType.V1), true);
    }

    @Override
    public void onScoreProblemNextAnother(@NonNull ScoreEntity entity) {
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        UIUtils.show(R.string.grade_next_score_success);
        getScoreActivity().replaceFragment(ScoreParameterEntity.newReplaceInstanceV1(entity.getExamGroupId(), entity.getMarkingGroupId(), entity.getStudentId(), entity.getStudentPaperTopic().getType() == QuestionType.FILL ? QuestionType.FILL : QuestionType.ANSWER, VersionType.V1), true);
    }

    @Override
    public void onScoreProblemPrevNetAnother(@NonNull ScoreEntity entity) {
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        UIUtils.show(R.string.grade_prev_score_success);
        getScoreActivity().replaceFragment(ScoreParameterEntity.newReplaceInstanceV1(entity.getExamGroupId(), entity.getMarkingGroupId(), entity.getStudentId(), entity.getStudentPaperTopic().getType() == QuestionType.FILL ? QuestionType.FILL : QuestionType.ANSWER, VersionType.V1), false);
    }

    @NonNull
    @Override
    public Activity getCurrentActivity() {
        return mActivity;
    }

    @Override
    public void netRequest(@GradeScoreType int type, boolean next) {
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        UIUtils.forceOffKeyboard(mActivity);
        scoreNetType = type;
        switch (type) {
            case GradeScoreType.FIRST:
                mPresenter.onScoreRequest(getScoreActivity().getExamGroupId(), getScoreActivity().getMarkingGroupId(), getScoreActivity().getClassId(), getScoreType(), first, Booleans.FALSE);
                break;
            case GradeScoreType.PREV:
                mPresenter.onScorePrevRequest(getScoreActivity().getExamGroupId(), getScoreActivity().getMarkingGroupId(), getScoreActivity().getClassId(), next ? getScoreActivity().getNextStudentId() : getScoreActivity().getPrevStudentId(), getScoreType(), Booleans.FALSE);
                break;
            case GradeScoreType.NEXT:
                mPresenter.onScoreNextRequest(getScoreActivity().getExamGroupId(), getScoreActivity().getMarkingGroupId(), getScoreActivity().getClassId(), next ? getScoreActivity().getNextStudentId() : getScoreActivity().getPrevStudentId(), getScoreType(), Booleans.FALSE);
                break;
            case GradeScoreType.UN:
                mPresenter.onScoreUnRequest(getScoreActivity().getExamGroupId(), getScoreActivity().getMarkingGroupId(), getScoreActivity().getClassId(), getScoreType(), Booleans.FALSE);
                break;
            case GradeScoreType.UN_NEXT:
                mPresenter.onScoreUnNextRequest(getScoreActivity().getExamGroupId(), getScoreActivity().getMarkingGroupId(), getScoreActivity().getClassId(), getScoreType(), Booleans.FALSE);
                break;
            case GradeScoreType.PROBLEM:
                mPresenter.onScoreProblemRequest(getScoreActivity().getExamGroupId(), getScoreActivity().getMarkingGroupId(), next ? getScoreActivity().getNextStudentId() : getScoreActivity().getPrevStudentId(), getScoreType(), first, Booleans.FALSE);
                break;
            case GradeScoreType.PROBLEM_PREV:
                mPresenter.onScoreProblemPrevRequest(getScoreActivity().getExamGroupId(), getScoreActivity().getMarkingGroupId(), next ? getScoreActivity().getNextStudentId() : getScoreActivity().getPrevStudentId(), getScoreType(), Booleans.FALSE);
                break;
            case GradeScoreType.PROBLEM_NEXT:
                mPresenter.onScoreProblemNextRequest(getScoreActivity().getExamGroupId(), getScoreActivity().getMarkingGroupId(), next ? getScoreActivity().getNextStudentId() : getScoreActivity().getPrevStudentId(), getScoreType(), Booleans.FALSE);
                break;
        }
    }

    @Override
    public void onPostSubmit(@NonNull ArrayList<SubmitScoreBody> body, @NonNull String fraction, @TouchMode int type) {
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        if (problem) {
            mPresenter.onProblemSubmit(getScoreActivity().getExamGroupId(), getScoreActivity().getMarkingGroupId(), getScoreActivity().getClassId(), body, fraction, type);
        } else {
            mPresenter.onSubmit(getScoreActivity().getExamGroupId(), getScoreActivity().getMarkingGroupId(), getScoreActivity().getClassId(), body, fraction, type);
        }
    }

    /**
     * 初始化适配器
     *
     * @return {@link PagerAdapter}
     */
    @NonNull
    public abstract PagerAdapter initPageAdapter();

    /**
     * @return 试卷类型提示语flag
     */
    @Deprecated
    public abstract boolean hasSubmit();

    /**
     * 更新试题数据
     */
    public abstract void onChangedUI();

    /**
     * 获取试题类型
     *
     * @return {@link QuestionType}
     */
    @QuestionType
    public abstract int getScoreType();

    /**
     * 更新UI数据
     */
    public void onChangedParentUI() {
        if (UIUtils.checkNotNull(getScoreActivity()) && getScoreType() == QuestionType.ANSWER) {
            getScoreActivity().onRefreshAnswerName();
        }
        onRefreshKeyboard();
        getScoreActivity().onRefreshTitle();
        getScoreActivity().onRefreshPercentageAndSchedule();
        if (getScoreType() == QuestionType.ANSWER) {
            getScoreActivity().onRefreshAnswerMaxScore();
        }
    }

    /**
     * 上一页
     */
    public void scoreViewPagerPrev() {
        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
    }

    /**
     * 下一页
     */
    public void scoreViewPagerNext() {
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
    }

    /**
     * 更新键盘
     */
    private void onRefreshKeyboard() {
        if (UIUtils.checkNotNull(getScoreActivity())) {
            getScoreActivity().onRefreshKeyboard();
        }
    }

    @Nullable
    public OldScoreActivity getScoreActivity() {
        return scoreActivity;
    }

    @GradeScoreType
    public int getCurrentNetType() {
        return scoreNetType;
    }

    public boolean isProblem() {
        return problem;
    }

    public boolean isMixing() {
        return mixing;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }
}
