package com.demo.template.ui.template.newx.fragment;

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
import com.android.library.net.body.read.ArbitrationTaskSubmitBody;
import com.android.library.net.body.read.ProblemTaskSubmitBody;
import com.android.library.net.body.read.TaskSubmitBody;
import com.android.library.net.entity.template.PairsMyProgressEntity;
import com.android.library.net.entity.template.ScoreParameterEntity;
import com.android.library.net.entity.template.ScoreTaskEntity;
import com.demo.template.entity.ScoreZipV2Entity;
import com.android.library.net.entity.template.TaskSubmitEntity;
import com.android.library.widget.custom.CustomViewPager;
import com.demo.template.R;
import com.demo.template.annotation.BundleKey;
import com.demo.template.annotation.GradeScoreType;
import com.demo.template.annotation.ScorePageState;
import com.demo.template.annotation.TouchMode;
import com.demo.template.factory.ScoreKeyboardFactory;
import com.demo.template.listener.action.SimpleOnPageChangeAction;
import com.demo.template.listener.read.OnScoreChildOwnListener;
import com.demo.template.ui.template.newx.activity.ScoreActivity;
import com.demo.template.ui.template.newx.impl.ScoreImpl;
import com.demo.template.ui.template.newx.impl.ScorePresenterImpl;
import com.demo.template.ui.template.newx.view.ScoreView;

import butterknife.BindView;


/**
 * @author y
 */
public abstract class BaseScoreFragment extends MVPFragment<ScorePresenterImpl, ScoreZipV2Entity> implements SimpleOnPageChangeAction, ScoreView, OnScoreChildOwnListener {

    @BindView(R.id.score_child_viewpager)
    CustomViewPager viewPager;

    private ScoreActivity scoreActivity;
    @GradeScoreType
    private int scoreNetType;
    private boolean problem;
    private boolean mixing;
    private boolean arbitration;
    private boolean first = true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        scoreActivity = (ScoreActivity) mActivity;
    }

    @Override
    protected void initCreate(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ScoreParameterEntity parameterEntity = bundle.getParcelable(BundleKey.GRADE_SCORE_ENTITY);
        if (UIUtils.checkNull(parameterEntity)) {
            parameterEntity = ScoreParameterEntity.newInstance();
        }
        problem = parameterEntity.isProblem();
        mixing = parameterEntity.isMixing();
        arbitration = parameterEntity.isArbitration();
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
        if (arbitration) {
            netRequest(GradeScoreType.ARBITRATION);
        } else if (problem) {
            netRequest(GradeScoreType.PROBLEM);
        } else if (UIUtils.checkNotNull(getScoreActivity()) && getScoreActivity().isShowUnScore()) {
            netRequest(GradeScoreType.UN);
        } else {
            netRequest(GradeScoreType.FIRST);
        }
    }

    @Override
    protected ScorePresenterImpl initPresenter() {
        return new ScoreImpl(this);
    }

    /**
     * 显示未阅卷
     */
    public void onShowUnAnswerScore() {
        netRequest(UIUtils.checkNotNull(getScoreActivity()) && getScoreActivity().isShowUnScore() ? GradeScoreType.UN : GradeScoreType.FIRST);
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
    public void onSubmitSuccess(@NonNull TaskSubmitEntity entity, @NonNull String fraction, @TouchMode int type) {
//        UIUtils.show(R.string.grade_submit_success);
        if (UIUtils.checkNotNull(getScoreActivity())) {
            PairsMyProgressEntity pairsMyProgressEntity = new PairsMyProgressEntity();
            pairsMyProgressEntity.setAccuracyRate(entity.getAccuracyRate());
            pairsMyProgressEntity.setMarkingNum(entity.getMarkingNum());
            pairsMyProgressEntity.setMarkedNum(entity.getMarkedNum());
            getScoreActivity().onRefreshProgressItem(pairsMyProgressEntity);
            getScoreActivity().onRefreshPercentageAndSchedule();
        }
    }

    @Override
    public void onScoreFirstNetSuccess(@NonNull ScoreZipV2Entity entity) {
        if (UIUtils.checkNotNull(getScoreActivity())) {
            getScoreActivity().onRefreshTopicAnswer(entity.getTopicAnswerEntity());
            getScoreActivity().onRefreshScoreList(entity.getTaskList());
        }
        if (isFirst()) {
            viewPager.setCurrentItem(Integer.MAX_VALUE / 2);
            setFirst(false);
            if (UIUtils.checkNull(GreenDaoManager.queryKeyboardV2(getScoreActivity().getTopicId()))) {
                ScoreKeyboardFactory.initScoreKeyboardV2(getScoreActivity().getTopicId(), getScoreActivity().getMaxTopicScore());
            }
            onChangedParentUI();
        } else {
            onChangedUI();
        }
    }

    @Override
    public void onScoreUnNetSuccess(@NonNull ScoreZipV2Entity entity) {
        if (UIUtils.checkNotNull(getScoreActivity())) {
            getScoreActivity().onRefreshScoreList(entity.getTaskList());
            getScoreActivity().setShowUnScore(true);
        }
        onChangedUI();
    }

    @Override
    public void onArbitrationScoreFirstNetSuccess(@NonNull ScoreZipV2Entity entity) {
        onScoreFirstNetSuccess(entity);
    }

    @Override
    public void onScoreProblemNetSuccess(@NonNull ScoreZipV2Entity zipEntity) {
        if (UIUtils.checkNotNull(zipEntity.getTaskList()) && !UIUtils.isEmpty(zipEntity.getTaskList().getList())) {
            for (int i = 0; i < zipEntity.getTaskList().getList().size(); i++) {
                if (i == 0) {
                    zipEntity.getTaskList().getList().get(i).setSelect(true);
                }
            }
        }
        onScoreFirstNetSuccess(zipEntity);
    }

    @Override
    public void onScoreNextNetSuccess(@NonNull ScoreZipV2Entity zipEntity) {
        if (UIUtils.checkNotNull(getScoreActivity())) {
            getScoreActivity().onRefreshScoreList(zipEntity.getTaskList());
        }
        scoreViewPagerNext();
    }

    @Override
    public void onArbitrationScoreNextNetSuccess(@NonNull ScoreZipV2Entity entity) {
        if (UIUtils.checkNotNull(getScoreActivity())) {
            getScoreActivity().onRefreshScoreList(entity.getTaskList());
        }
        scoreViewPagerNext();
    }

    @Override
    public void onArbitrationScorePrevNetSuccess(@NonNull ScoreZipV2Entity entity) {
        if (UIUtils.checkNotNull(getScoreActivity())) {
            getScoreActivity().onRefreshScoreList(entity.getTaskList());
        }
        scoreViewPagerPrev();
    }

    @Override
    public void onScorePrevNetSuccess(@NonNull ScoreZipV2Entity zipEntity) {
        if (UIUtils.checkNotNull(getScoreActivity())) {
            getScoreActivity().onRefreshScoreList(zipEntity.getTaskList());
        }
        scoreViewPagerPrev();
    }

    @Override
    public void onScoreUnNextNetSuccess(@NonNull ScoreZipV2Entity zipEntity) {
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        if (getScoreActivity().hasLastItem() && zipEntity.getTaskList().getList().isEmpty()) {
            onScoreUnNextNetNoMore();
            return;
        }
        if (!getScoreActivity().hasLastItem() && zipEntity.getTaskList().getList().isEmpty()) {
            onScoreUnNextNetAnother();
            return;
        }
//        if (UIUtils.checkNotNull(getScoreActivity())) {
//            getScoreActivity().onRefreshScoreList(zipEntity.getTaskList());
//        }
        getScoreActivity().onRefreshScoreList(zipEntity.getTaskList());
        scoreViewPagerNext();
    }

    @Override
    public void onArbitrationScoreNextNetAnother() {
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        PairsMyProgressEntity nextProgressItem = getScoreActivity().getNextProgressItem();
        if (UIUtils.checkNull(nextProgressItem)) {
            return;
        }
        UIUtils.show(R.string.grade_next_score_success);
        getScoreActivity().replaceFragment(ScoreParameterEntity.newReplaceInstanceV2(nextProgressItem.getTopicType() == QuestionType.FILL ? QuestionType.FILL : QuestionType.ANSWER, nextProgressItem.getExamGroupId(), nextProgressItem.getTopicId(), null, VersionType.V2), true);
    }


    @Override
    public void onArbitrationScorePrevNetAnother() {
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        PairsMyProgressEntity prevProgressItem = getScoreActivity().getPrevProgressItem();
        if (UIUtils.checkNull(prevProgressItem)) {
            return;
        }
        UIUtils.show(R.string.grade_prev_score_success);
        getScoreActivity().replaceFragment(ScoreParameterEntity.newReplaceInstanceV2(prevProgressItem.getTopicType() == QuestionType.FILL ? QuestionType.FILL : QuestionType.ANSWER, prevProgressItem.getExamGroupId(), prevProgressItem.getTopicId(), null, VersionType.V2), false);
    }

    @Override
    public void onScoreNextNetAnother() {
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        PairsMyProgressEntity nextProgressItem = getScoreActivity().getNextProgressItem();
        if (UIUtils.checkNull(nextProgressItem)) {
            return;
        }
        UIUtils.show(R.string.grade_next_score_success);
        getScoreActivity().replaceFragment(ScoreParameterEntity.newReplaceInstanceV2(nextProgressItem.getTopicType() == QuestionType.FILL ? QuestionType.FILL : QuestionType.ANSWER, nextProgressItem.getExamGroupId(), nextProgressItem.getTopicId(), null, VersionType.V2), true);
    }

    @Override
    public void onScorePrevNetAnother() {
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        PairsMyProgressEntity prevProgressItem = getScoreActivity().getPrevProgressItem();
        if (UIUtils.checkNull(prevProgressItem)) {
            return;
        }
        UIUtils.show(R.string.grade_prev_score_success);
        getScoreActivity().replaceFragment(ScoreParameterEntity.newReplaceInstanceV2(prevProgressItem.getTopicType() == QuestionType.FILL ? QuestionType.FILL : QuestionType.ANSWER, prevProgressItem.getExamGroupId(), prevProgressItem.getTopicId(), null, VersionType.V2), false);
    }

    @Override
    public void onScoreUnNextNetAnother() {
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        PairsMyProgressEntity prevProgressItem = getScoreActivity().getNextProgressItem();
        if (UIUtils.checkNull(prevProgressItem)) {
            return;
        }
        UIUtils.show(R.string.grade_next_score_success);
        getScoreActivity().replaceFragment(ScoreParameterEntity.newReplaceInstanceV2(prevProgressItem.getTopicType() == QuestionType.FILL ? QuestionType.FILL : QuestionType.ANSWER, prevProgressItem.getExamGroupId(), prevProgressItem.getTopicId(), null, VersionType.V2), true);
    }

    @Override
    public void onScoreProblemNextAnother(@NonNull ScoreTaskEntity entity) {
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        UIUtils.show(R.string.grade_next_score_success);
        getScoreActivity().replaceFragment(ScoreParameterEntity.newReplaceInstanceV2(entity.getTopicType() == QuestionType.FILL ? QuestionType.FILL : QuestionType.ANSWER, entity.getExamGroupId(), entity.getTopicId(), entity.getStudentId(), VersionType.V2), true);
    }

    @Override
    public void onScoreProblemPrevNetAnother(@NonNull ScoreTaskEntity entity) {
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        UIUtils.show(R.string.grade_prev_score_success);
        getScoreActivity().replaceFragment(ScoreParameterEntity.newReplaceInstanceV2(entity.getTopicType() == QuestionType.FILL ? QuestionType.FILL : QuestionType.ANSWER, entity.getExamGroupId(), entity.getTopicId(), entity.getStudentId(), VersionType.V2), false);
    }

    @NonNull
    @Override
    public Activity getCurrentActivity() {
        return mActivity;
    }

    @Override
    public void netRequest(@GradeScoreType int type) {
        if (UIUtils.checkNull(getScoreActivity()) || UIUtils.checkNull(getScoreActivity().getParameter())) {
            return;
        }
        UIUtils.forceOffKeyboard(mActivity);
        scoreNetType = type;
        switch (type) {
            case GradeScoreType.FIRST:
                mPresenter.onScoreRequest(getScoreActivity().getExamGroupId(), getScoreActivity().getTopicId(), getScoreType(), getScoreActivity().getCurrentItemPageNum(), first, Booleans.FALSE);
                break;
            case GradeScoreType.NEXT:
                int nextPageState = getScoreActivity().hasNextPageState();
                if (nextPageState == ScorePageState.NO_NEXT) {
                    onScoreNextNetNoMore();
                } else if (nextPageState == ScorePageState.ANOTHER_NEXT) {
                    onScoreNextNetAnother();
                } else {
                    mPresenter.onScoreNextRequest(getScoreActivity().getExamGroupId(), getScoreActivity().getTopicId(), getScoreType(), getScoreActivity().getCurrentItemPageNum() + 1, first, Booleans.FALSE);
                }
                break;
            case GradeScoreType.PREV:
                int prevPageState = getScoreActivity().hasPrevPageState();
                if (prevPageState == ScorePageState.NO_PREV) {
                    onScorePrevNetNoMore();
                } else if (prevPageState == ScorePageState.ANOTHER_PREV) {
                    onScorePrevNetAnother();
                } else {
                    mPresenter.onScorePrevRequest(getScoreActivity().getExamGroupId(), getScoreActivity().getTopicId(), getScoreType(), getScoreActivity().getCurrentItemPageNum() - 1, first, Booleans.FALSE);
                }
                break;
            case GradeScoreType.UN:
                boolean unScore = getScoreActivity().hasUnScore();
                if (unScore) {
                    mPresenter.onScoreUnRequest(getScoreActivity().getExamGroupId(), getScoreActivity().getTopicId(), getScoreType(), Booleans.FALSE);
                } else {
                    onShowUnEmpty();
                }
                break;
            case GradeScoreType.UN_NEXT:
                mPresenter.onScoreUnNextRequest(getScoreActivity().getExamGroupId(), getScoreActivity().getTopicId(), getScoreType(), Booleans.FALSE);
                break;
            case GradeScoreType.PROBLEM:
                mPresenter.onScoreProblemRequest(getScoreActivity().getExamGroupId(), getScoreActivity().getTopicId(), getScoreActivity().getParameter().getStudentId(), getScoreType(), first, Booleans.FALSE);
                break;
            case GradeScoreType.PROBLEM_PREV:
                mPresenter.onScoreProblemPrevRequest(getScoreActivity().getExamGroupId(), getScoreActivity().getTopicId(), getScoreActivity().getParameter().getStudentId(), getScoreType(), first, Booleans.FALSE);
                break;
            case GradeScoreType.PROBLEM_NEXT:
                mPresenter.onScoreProblemNextRequest(getScoreActivity().getExamGroupId(), getScoreActivity().getTopicId(), getScoreActivity().getParameter().getStudentId(), getScoreType(), first, Booleans.FALSE);
                break;
            case GradeScoreType.ARBITRATION_NEXT:
                int nextArbitrationPageState = getScoreActivity().hasNextPageState();
                if (nextArbitrationPageState == ScorePageState.NO_NEXT) {
                    onArbitrationScoreNextNetNoMore();
                } else if (nextArbitrationPageState == ScorePageState.ANOTHER_NEXT) {
                    onArbitrationScoreNextNetAnother();
                } else {
                    mPresenter.onArbitrationScoreNextRequest(getScoreActivity().getExamGroupId(), getScoreActivity().getTopicId(), getScoreType(), getScoreActivity().getCurrentItemPageNum() + 1, first, Booleans.FALSE);
                }
                break;
            case GradeScoreType.ARBITRATION_PREV:
                int prevArbitrationPageState = getScoreActivity().hasPrevPageState();
                if (prevArbitrationPageState == ScorePageState.NO_PREV) {
                    onArbitrationScorePrevNetNoMore();
                } else if (prevArbitrationPageState == ScorePageState.ANOTHER_PREV) {
                    onArbitrationScorePrevNetAnother();
                } else {
                    mPresenter.onArbitrationScorePrevRequest(getScoreActivity().getExamGroupId(), getScoreActivity().getTopicId(), getScoreType(), getScoreActivity().getCurrentItemPageNum() - 1, first, Booleans.FALSE);
                }
                break;
            case GradeScoreType.ARBITRATION:
                mPresenter.onArbitrationScoreRequest(getScoreActivity().getExamGroupId(), getScoreActivity().getTopicId(), getScoreType(), getScoreActivity().getCurrentItemPageNum(), first, Booleans.FALSE);
                break;
        }
    }

    @Override
    public void onPostSubmit(@NonNull TaskSubmitBody body, @NonNull String fraction, @TouchMode int type) {
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        mPresenter.onSubmit(body, fraction, type);
    }

    @Override
    public void onProblemPostSubmit(@NonNull ProblemTaskSubmitBody body, @NonNull String fraction, int type) {
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        mPresenter.onProblemSubmit(body, fraction, type);
    }

    @Override
    public void onArbitrationPostSubmit(@NonNull ArbitrationTaskSubmitBody body, @NonNull String fraction, int type) {
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        mPresenter.onArbitrationSubmit(body, fraction, type);
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
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        if (getScoreType() == QuestionType.ANSWER) {
            getScoreActivity().onRefreshAnswerName();
            getScoreActivity().onRefreshAnswerMaxScore();
        }
        onRefreshKeyboard();
        getScoreActivity().onRefreshTitle();
        getScoreActivity().onRefreshPercentageAndSchedule();
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
    public ScoreActivity getScoreActivity() {
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

    public boolean isArbitration() {
        return arbitration;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }
}
