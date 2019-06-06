package com.demo.template.ui.grade.newx.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.library.bridge.annotation.TopicType;
import com.android.library.bridge.util.NumberUtils;
import com.android.library.bridge.util.UIUtils;
import com.android.library.net.body.read.ArbitrationTaskSubmitBody;
import com.android.library.net.body.read.ProblemTaskSubmitBody;
import com.android.library.net.body.read.TaskSubmitBody;
import com.android.library.net.entity.template.PairsMyProgressEntity;
import com.android.library.net.entity.template.ScoreParameterEntity;
import com.android.library.net.entity.template.ScoreTaskEntity;
import com.android.library.net.entity.template.TaskSubmitEntity;
import com.demo.template.R;
import com.demo.template.annotation.BundleKey;
import com.demo.template.annotation.GradeScoreType;
import com.demo.template.annotation.TouchMode;
import com.demo.template.factory.ScoreKeyboardFactory;
import com.demo.template.ui.grade.newx.adapter.FillScoreAdapter;
import com.demo.template.ui.grade.newx.view.FillScoreView;
import com.demo.template.utils.MDialogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author y
 */
public class FillScoreFragment extends BaseScoreFragment implements FillScoreView, MaterialDialog.InputCallback {

    public static final String ALL_TRUE = "allTrue";
    public static final String ALL_FALSE = "allFalse";
    @Nullable
    private FillScoreAdapter pagerAdapter;

    public static FillScoreFragment newInstance(ScoreParameterEntity entity) {
        FillScoreFragment fillScoreFragment = new FillScoreFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BundleKey.GRADE_SCORE_ENTITY, entity);
        fillScoreFragment.setArguments(bundle);
        return fillScoreFragment;
    }

    @NonNull
    @Override
    public PagerAdapter initPageAdapter() {
        return pagerAdapter = new FillScoreAdapter(getChildFragmentManager());
    }

    @Override
    public void onChangedUI() {
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        pagerAdapter.onChangedUI(getScoreActivity().getFillEntity());
    }

    @Override
    public void onShowItem() {
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        MDialogUtils.fillScoreNum(mActivity, ScoreKeyboardFactory.getTopicCountV2(getScoreActivity().getTopicId()), this);
    }

    @Deprecated
    @Override
    public boolean hasSubmit() {
        if (UIUtils.checkNull(pagerAdapter)) {
            return false;
        }
        return !UIUtils.checkNull(pagerAdapter.getCurrentFragment()) && pagerAdapter.getCurrentFragment().hasSubmit();
    }

    @TopicType
    @Override
    public int getScoreType() {
        return TopicType.FILL;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.grade_fragment_fill_score;
    }

    @Override
    public void onScrollRight() {
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        if (UIUtils.checkNotNull(getScoreActivity()) && getScoreActivity().isShowUnScore()) {
            return;
        }
        if (UIUtils.checkNull(getScoreActivity().getFillEntity())) {
            return;
        }
        if (UIUtils.checkNull(pagerAdapter.getCurrentFragment())) {
            return;
        }
        onPrevPage();
    }

    @Override
    public void onPrevPage() {
        netRequest(isArbitration() ? GradeScoreType.ARBITRATION_PREV : isProblem() ? GradeScoreType.PROBLEM_PREV : GradeScoreType.PREV);
    }

    @Override
    public void onScrollLeft() {
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        FillChildFragment childFragment = pagerAdapter.getCurrentFragment();
        if (UIUtils.checkNull(getScoreActivity().getFillEntity())) {
            return;
        }
        if (UIUtils.checkNull(childFragment)) {
            return;
        }
        if (UIUtils.checkNotNull(getScoreActivity()) && getScoreActivity().isShowUnScore() && childFragment.hasSubmit()) {
            UIUtils.show(R.string.grade_score_tips);
            return;
        }
        if (UIUtils.checkNotNull(getScoreActivity()) && getScoreActivity().isAutomaticSubmit() && childFragment.hasSubmit()) {
            UIUtils.show(R.string.grade_score_tips);
            return;
        }
        onNextPage();
    }

    @Override
    public void onNextPage() {
        netRequest(isArbitration() ? GradeScoreType.ARBITRATION_NEXT : isProblem() ? GradeScoreType.PROBLEM_NEXT : UIUtils.checkNotNull(getScoreActivity()) && getScoreActivity().isShowUnScore() ? GradeScoreType.UN_NEXT : GradeScoreType.NEXT);
    }

    @Override
    public void onSubmitAll(boolean hasTrue) {
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        List<ScoreTaskEntity> scoreEntityList = pagerAdapter.getCurrentData();
        if (UIUtils.checkNull(scoreEntityList)) {
            return;
        }
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        if (UIUtils.checkNull(getScoreActivity().getCurrentProgressItem())) {
            return;
        }
        if (isArbitration()) {
            ArrayList<ArbitrationTaskSubmitBody.StudentsBean> bodyList = new ArrayList<>();
            for (ScoreTaskEntity datum : scoreEntityList) {
                bodyList.add(new ArbitrationTaskSubmitBody.StudentsBean(
                        hasTrue ? getScoreActivity().getMaxTopicScore() : 0,
                        datum.getSptrId(),
                        datum.getStudentId()));
            }
            ArbitrationTaskSubmitBody taskSubmitBody = new ArbitrationTaskSubmitBody(getScoreActivity().getTopicId(), getScoreActivity().getExamGroupId(), bodyList);
            onArbitrationPostSubmit(taskSubmitBody, hasTrue ? ALL_TRUE : ALL_FALSE, TouchMode.UNKNOWN);
            return;
        }
        ArrayList<TaskSubmitBody.StudentsBean> bodyList = new ArrayList<>();
        for (ScoreTaskEntity datum : scoreEntityList) {
            bodyList.add(new TaskSubmitBody.StudentsBean(
                    isProblem() ? 1 : 0,
                    hasTrue ? getScoreActivity().getMaxTopicScore() : 0,
                    datum.getSptrId(),
                    datum.getStudentId(),
                    ""));
        }
        TaskSubmitBody taskSubmitBody = new TaskSubmitBody(getScoreActivity().getCurrentProgressItem().getBatchNo(), getScoreActivity().getExamGroupId(), getScoreActivity().getTopicId(), bodyList);
        onPostSubmit(taskSubmitBody, hasTrue ? ALL_TRUE : ALL_FALSE, TouchMode.UNKNOWN);
    }

    @Override
    public void onSubmit(@NonNull String fraction) {
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        ScoreTaskEntity submitEntity = pagerAdapter.getSubmitEntity();
        List<ScoreTaskEntity> scoreEntityList = pagerAdapter.getCurrentData();
        if (UIUtils.checkNull(submitEntity)) {
            return;
        }
        if (UIUtils.checkNull(scoreEntityList)) {
            return;
        }
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        boolean isUnknown = TextUtils.equals(fraction, UIUtils.getString(R.string.grade_unknown));
        if (isArbitration()) {
            arbitrationSubmit(fraction, submitEntity);
            return;
        }
        if (isProblem()) {
            ScoreTaskEntity answerEntity = getScoreActivity().getAnswerEntity();
            if (UIUtils.checkNull(answerEntity)) {
                return;
            }
            ProblemTaskSubmitBody.StudentsBean studentsBean = new ProblemTaskSubmitBody.StudentsBean(
                    answerEntity.getBatchNo(),
                    NumberUtils.parseDouble(fraction),
                    answerEntity.getStudentId(),
                    answerEntity.getTopicId()
            );
            ArrayList<ProblemTaskSubmitBody.StudentsBean> studentsBeans = new ArrayList<>();
            studentsBeans.add(studentsBean);
            ProblemTaskSubmitBody problemTaskSubmitBody = new ProblemTaskSubmitBody(getScoreActivity().getExamGroupId(), studentsBeans);
            onProblemPostSubmit(problemTaskSubmitBody, String.valueOf(studentsBean.getScoring()), TouchMode.UNKNOWN);
            return;
        }
        PairsMyProgressEntity currentProgressItem = getScoreActivity().getCurrentProgressItem();
        if (UIUtils.checkNull(currentProgressItem)) {
            return;
        }
        ArrayList<TaskSubmitBody.StudentsBean> bodyList = new ArrayList<>();
        bodyList.add(new TaskSubmitBody.StudentsBean(
                isUnknown ? 1 : 0,
                isUnknown ? 0 : NumberUtils.parseDouble(fraction),
                submitEntity.getSptrId(),
                submitEntity.getStudentId(),
                ""));
        TaskSubmitBody taskSubmitBody = new TaskSubmitBody(currentProgressItem.getBatchNo(), submitEntity.getExamGroupId(), submitEntity.getTopicId(), bodyList);
        onPostSubmit(taskSubmitBody, fraction, TouchMode.UNKNOWN);
    }

    private void arbitrationSubmit(@NonNull String fraction, ScoreTaskEntity submitEntity) {
        ArrayList<ArbitrationTaskSubmitBody.StudentsBean> bodyList = new ArrayList<>();
        bodyList.add(new ArbitrationTaskSubmitBody.StudentsBean(
                NumberUtils.parseDouble(fraction),
                submitEntity.getSptrId(),
                submitEntity.getStudentId()));
        ArbitrationTaskSubmitBody taskSubmitBody = new ArbitrationTaskSubmitBody(submitEntity.getTopicId(), submitEntity.getExamGroupId(), bodyList);
        onArbitrationPostSubmit(taskSubmitBody, fraction, TouchMode.UNKNOWN);
    }

    @Override
    public void onSubmitSuccess(@NonNull TaskSubmitEntity entity, @NonNull String fraction, @TouchMode int type) {
        super.onSubmitSuccess(entity, fraction, type);
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        List<ScoreTaskEntity> currentData = pagerAdapter.getCurrentData();
        if (UIUtils.checkNull(currentData)) {
            return;
        }
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        if (TextUtils.equals(fraction, ALL_FALSE) || TextUtils.equals(fraction, ALL_TRUE)) {
            for (ScoreTaskEntity datum : currentData) {
                datum.setStatus(1);
                datum.setIsProblem(0);
                datum.setSelect(false);
                datum.setProblemStatus(0);
                datum.setScoring(TextUtils.equals(fraction, ALL_TRUE) ? getScoreActivity().getMaxTopicScore() : 0);
            }
            pagerAdapter.refresh();
            onAutomaticSubmit(type);
            return;
        }
        ScoreTaskEntity submitEntity = pagerAdapter.getSubmitEntity();
        int submitEntityPosition = pagerAdapter.getSubmitEntityPosition();
        if (UIUtils.checkNull(submitEntity)) {
            return;
        }
        submitEntity.setStatus(1);
        submitEntity.setIsProblem(TextUtils.equals(fraction, UIUtils.getString(R.string.grade_unknown)) ? 1 : 0);
        submitEntity.setSelect(false);
        submitEntity.setProblemStatus(isProblem() ? 1 : 0);
        submitEntity.setScoring(NumberUtils.parseDouble(fraction));
        pagerAdapter.refresh();
        pagerAdapter.smoothScrollToPosition(submitEntityPosition + 2);
        onAutomaticSubmit(type);
    }

    @Override
    public void onAutomaticSubmit(@TouchMode int type) {
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        if (UIUtils.checkNotNull(getScoreActivity()) && !getScoreActivity().isAutomaticSubmit()) {
            return;
        }
        if (!UIUtils.checkNull(pagerAdapter.getSubmitEntity())) {
            return;
        }
        onNextPage();
    }

    @Override
    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        ScoreKeyboardFactory.updateTopicCountV2(getScoreActivity().getTopicId(), NumberUtils.parseInt(input.toString()));
        int type = getCurrentNetType();
        if (getCurrentNetType() == GradeScoreType.ARBITRATION_NEXT || getCurrentNetType() == GradeScoreType.ARBITRATION_PREV) {
            type = GradeScoreType.ARBITRATION;
        } else if (getCurrentNetType() == GradeScoreType.NEXT || getCurrentNetType() == GradeScoreType.PREV) {
            type = GradeScoreType.FIRST;
        } else if (getCurrentNetType() == GradeScoreType.UN_NEXT) {
            type = GradeScoreType.UN;
        }
        netRequest(type);
    }
}
