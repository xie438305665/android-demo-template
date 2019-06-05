package com.demo.template.ui.grade.oldx.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.library.bridge.annotation.QuestionType;
import com.android.library.bridge.util.NumberUtils;
import com.android.library.bridge.util.UIUtils;
import com.android.library.net.body.read.SubmitScoreBody;
import com.android.library.net.entity.template.ScoreEntity;
import com.android.library.net.entity.template.ScoreParameterEntity;
import com.demo.template.entity.ScoreZipEntity;
import com.demo.template.R;
import com.demo.template.annotation.BundleKey;
import com.demo.template.annotation.GradeScoreType;
import com.demo.template.annotation.TouchMode;
import com.demo.template.factory.ScoreKeyboardFactory;
import com.demo.template.ui.grade.oldx.adapter.OldFillScoreAdapter;
import com.demo.template.ui.grade.oldx.view.OldFillScoreView;
import com.demo.template.utils.MDialogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author y
 */
@Deprecated
public class OldFillScoreFragment extends OldBaseScoreFragment implements OldFillScoreView, MaterialDialog.InputCallback {

    public static final String ALL_TRUE = "allTrue";
    public static final String ALL_FALSE = "allFalse";
    @Nullable
    private OldFillScoreAdapter pagerAdapter;

    public static OldFillScoreFragment newInstance(ScoreParameterEntity entity) {
        OldFillScoreFragment fillScoreFragment = new OldFillScoreFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BundleKey.GRADE_SCORE_ENTITY, entity);
        fillScoreFragment.setArguments(bundle);
        return fillScoreFragment;
    }

    @NonNull
    @Override
    public PagerAdapter initPageAdapter() {
        return pagerAdapter = new OldFillScoreAdapter(getChildFragmentManager());
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
        MDialogUtils.fillScoreNum(mActivity, ScoreKeyboardFactory.getTopicCountV1(getScoreActivity().getMarkingGroupId()), this);
    }

    @Deprecated
    @Override
    public boolean hasSubmit() {
        if (UIUtils.checkNull(pagerAdapter)) {
            return false;
        }
        return !UIUtils.checkNull(pagerAdapter.getCurrentFragment()) && pagerAdapter.getCurrentFragment().hasSubmit();
    }

    @QuestionType
    @Override
    public int getScoreType() {
        return QuestionType.FILL;
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
        netRequest(isProblem() ? GradeScoreType.PROBLEM_PREV : GradeScoreType.PREV, false);
    }

    @Override
    public void onScrollLeft() {
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        OldFillChildFragment childFragment = pagerAdapter.getCurrentFragment();
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
        netRequest(isProblem() ? GradeScoreType.PROBLEM_NEXT : UIUtils.checkNotNull(getScoreActivity()) && getScoreActivity().isShowUnScore() ? GradeScoreType.UN_NEXT : GradeScoreType.NEXT, true);
    }

    @Override
    public void onSubmitAll(boolean hasTrue) {
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        List<ScoreEntity> scoreEntityList = pagerAdapter.getCurrentData();
        if (UIUtils.checkNull(scoreEntityList)) {
            return;
        }
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        ArrayList<SubmitScoreBody> bodyList = new ArrayList<>();
        for (ScoreEntity datum : scoreEntityList) {
            bodyList.add(new SubmitScoreBody(
                    datum.getExamGroupId(),
                    0,
                    datum.getMarkingGroupId(),
                    datum.getStudentPaperTopic().getPaperId(),
                    hasTrue ? getScoreActivity().getMaxTopicScore() : 0,
                    datum.getStudentId(),
                    datum.getStudentPaperTopic().getTopicId(),
                    ""));
        }
        onPostSubmit(bodyList, hasTrue ? ALL_TRUE : ALL_FALSE, TouchMode.UNKNOWN);
    }

    @Override
    public void onSubmit(@NonNull String fraction) {
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        ScoreEntity submitEntity = pagerAdapter.getSubmitEntity();
        if (UIUtils.checkNull(submitEntity)) {
            return;
        }
        ArrayList<SubmitScoreBody> bodyList = new ArrayList<>();
        boolean isUnknown = TextUtils.equals(fraction, UIUtils.getString(R.string.grade_unknown));
        bodyList.add(new SubmitScoreBody(
                submitEntity.getExamGroupId(),
                isUnknown ? 1 : 0,
                submitEntity.getMarkingGroupId(),
                submitEntity.getStudentPaperTopic().getPaperId(),
                isUnknown ? 0 : NumberUtils.parseDouble(fraction),
                submitEntity.getStudentId(),
                submitEntity.getStudentPaperTopic().getTopicId(),
                ""));
        onPostSubmit(bodyList, fraction, TouchMode.UNKNOWN);
    }

    @Override
    public void onSubmitSuccess(@NonNull ScoreZipEntity entity, @NonNull String fraction, @TouchMode int type) {
        super.onSubmitSuccess(entity, fraction, type);
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        List<ScoreEntity> currentData = pagerAdapter.getCurrentData();
        if (UIUtils.checkNull(currentData)) {
            return;
        }
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        if (TextUtils.equals(fraction, ALL_FALSE) || TextUtils.equals(fraction, ALL_TRUE)) {
            for (ScoreEntity datum : currentData) {
                datum.setStatus(1);
                datum.setIsProblem(0);
                datum.setSelect(false);
                datum.setProblemStatus(0);
                datum.getStudentPaperTopic().setIsProblem(0);
                datum.getStudentPaperTopic().setScoring(TextUtils.equals(fraction, ALL_TRUE) ? getScoreActivity().getMaxTopicScore() : 0);
            }
            pagerAdapter.refresh();
            onAutomaticSubmit(type);
            return;
        }
        ScoreEntity submitEntity = pagerAdapter.getSubmitEntity();
        if (UIUtils.checkNull(submitEntity)) {
            return;
        }
        submitEntity.setStatus(1);
        submitEntity.setIsProblem(TextUtils.equals(fraction, UIUtils.getString(R.string.grade_unknown)) ? 1 : 0);
        submitEntity.setSelect(false);
        submitEntity.setProblemStatus(isProblem() ? 1 : 0);
        submitEntity.getStudentPaperTopic().setIsProblem(TextUtils.equals(fraction, UIUtils.getString(R.string.grade_unknown)) ? 1 : 0);
        submitEntity.getStudentPaperTopic().setScoring(NumberUtils.parseDouble(fraction));
        pagerAdapter.refresh();
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
        ScoreKeyboardFactory.updateTopicCountV1(getScoreActivity().getMarkingGroupId(), NumberUtils.parseInt(input.toString()));
        int type = getCurrentNetType();
        if (getCurrentNetType() == GradeScoreType.NEXT || getCurrentNetType() == GradeScoreType.PREV) {
            type = GradeScoreType.FIRST;
        } else if (getCurrentNetType() == GradeScoreType.UN_NEXT) {
            type = GradeScoreType.UN;
        }
        netRequest(type, true);
    }
}
