package com.demo.template.ui.read.oldx.fragment;

import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

import com.android.library.bridge.annotation.QuestionType;
import com.android.library.bridge.util.BitmapUtils;
import com.android.library.bridge.util.NumberUtils;
import com.android.library.bridge.util.UIUtils;
import com.android.library.bridge.util.ViewUtils;
import com.android.library.db.GreenDaoManager;
import com.android.library.db.entity.KeyboardEntity;
import com.android.library.net.body.read.SubmitScoreBody;
import com.android.library.net.entity.template.AnnotationEntity;
import com.android.library.net.entity.template.ScoreEntity;
import com.android.library.net.entity.template.ScoreParameterEntity;
import com.demo.template.entity.ScoreZipEntity;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.demo.template.R;
import com.demo.template.annotation.BundleKey;
import com.demo.template.annotation.GradeScoreType;
import com.demo.template.annotation.TouchMode;
import com.demo.template.listener.action.OnAnswerAnnotationAction;
import com.demo.template.ui.activity.NoteActivity;
import com.demo.template.ui.read.common.adapter.AnswerScoreAdapter;
import com.demo.template.ui.read.common.dialog.AnswerScoreMoreDialog;
import com.demo.template.ui.read.oldx.view.OldAnswerScoreView;
import com.demo.template.utils.MDialogUtils;
import com.demo.template.utils.ScoreUtils;
import com.demo.template.widget.answer.AnswerAnnotationLayout;
import com.image.edit.OnEditImageListener;
import com.status.layout.Status;
import com.xadapter.adapter.XRecyclerViewAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Deprecated
public class OldAnswerScoreFragment extends OldBaseScoreFragment implements OldAnswerScoreView,
        AnswerScoreMoreDialog.ScoreMoreListener,
        OnEditImageListener,
        SubsamplingScaleImageView.OnStateChangedListener,
        OnAnswerAnnotationAction {

    @BindView(R.id.answer_previous_page)
    AppCompatImageView prevImage;
    @BindView(R.id.answer_next_page)
    AppCompatImageView nextImage;
    @BindView(R.id.annotation_root_view)
    AnswerAnnotationLayout annotationLayout;
    @Nullable
    private AnswerScoreAdapter pagerAdapter;

    public static OldAnswerScoreFragment newInstance(ScoreParameterEntity entity) {
        OldAnswerScoreFragment fragment = new OldAnswerScoreFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BundleKey.GRADE_SCORE_ENTITY, entity);
        fragment.setArguments(bundle);
        return fragment;
    }

    @OnClick({R.id.answer_previous_page, R.id.answer_next_page})
    public void onClicked(View view) {
        int id = view.getId();
        if (id == R.id.answer_previous_page) {
            onPrevPage();
        } else if (id == R.id.answer_next_page) {
            onNextPage();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        View view = getView();
        if (view == null) {
            return;
        }
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK && !isProblem() && annotationLayout.callOutVisible()) {
                openAnnotationUnFold();
                return true;
            }
            return false;
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        annotationLayout.init(isProblem(), false);
        annotationLayout.setOnAnswerAnnotationAction(this);
        super.onActivityCreated(savedInstanceState);
    }

    @NonNull
    @Override
    public PagerAdapter initPageAdapter() {
        return pagerAdapter = new AnswerScoreAdapter(this, this);
    }

    @Override
    public void onCenterChanged(PointF pointF, int i) {
    }

    @Override
    public void onScaleChanged(float v, int i) {
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        if (pagerAdapter.getMinScale() == -1) {
            return;
        }
        if (v > pagerAdapter.getMinScale() * 1.5) {
            ViewUtils.visibleView(nextImage, UIUtils.checkNotNull(getScoreActivity()) && getScoreActivity().isShowUnScore() ? null : prevImage);
        } else {
            ViewUtils.goneView(nextImage, prevImage);
        }
    }

    @Override
    public void scoreViewPagerPrev() {
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        pagerAdapter.setImageViewState(pagerAdapter.getCurrentViewState());
        pagerAdapter.quiteEdit();
        super.scoreViewPagerPrev();
    }

    @Override
    public void scoreViewPagerNext() {
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        pagerAdapter.setImageViewState(pagerAdapter.getCurrentViewState());
        pagerAdapter.quiteEdit();
        super.scoreViewPagerNext();
    }

    @Override
    public void onPageSelected(int position) {
        changeFraction();
        annotationLayout.closeAnnotation();
        if (UIUtils.checkNotNull(getScoreActivity())) {
            getScoreActivity().onClearAnswerEditTextFocus();
        }
        if (UIUtils.checkNotNull(getScoreActivity()) && !UIUtils.checkNull(getScoreActivity().getAnswerEntity())) {
            getScoreActivity().onRefreshAnswerName();
        }
    }

    @Override
    public void onChangedUI() {
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        if (getScoreActivity().getAnswerEntity() != null && getScoreActivity().getAnswerEntity().getStudentPaperTopic() != null) {
            pagerAdapter.displayFile(getScoreActivity().getAnswerEntity().getStudentPaperTopic().getFile());
        }
        ViewUtils.goneView(prevImage, nextImage);
        onPageSelected(0);
    }

    @Override
    public void onScrollRight() {
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        if (UIUtils.checkNotNull(getScoreActivity()) && getScoreActivity().isShowUnScore()) {
            return;
        }
        if (pagerAdapter.noScroll()) {
            return;
        }
        if (ViewUtils.visible(nextImage)) {
            return;
        }
        onPrevPage();
    }

    @Override
    public void onPrevPage() {
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        String fraction = UIUtils.checkNotNull(getScoreActivity()) ? getScoreActivity().getAnswerFraction() : "";
        if (ScoreUtils.checkFraction(fraction)) {
            UIUtils.show(R.string.grade_edit_image_submit_error);
            return;
        }
        if (getScoreActivity().isAutomaticSubmit() && TextUtils.isEmpty(fraction)) {
            UIUtils.show(R.string.grade_score_tips);
            return;
        }
        if (ScoreUtils.scrollHasSubmit(getScoreActivity().getAnswerEntity(), fraction, pagerAdapter.cacheEmpty())) {
            prevRequest();
            return;
        }
        onScoreMoreSubmit(null, TouchMode.PREV, getScoreActivity().getAnswerFraction());
    }

    private void prevRequest() {
        netRequest(isProblem() ? GradeScoreType.PROBLEM_PREV : GradeScoreType.PREV, false);
    }

    @Override
    public void onScrollLeft() {
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        if (pagerAdapter.noScroll()) {
            return;
        }
        if (ViewUtils.visible(nextImage)) {
            return;
        }
        onNextPage();
    }

    @Override
    public void onNextPage() {
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        String fraction = UIUtils.checkNotNull(getScoreActivity()) ? getScoreActivity().getAnswerFraction() : "";
        if (ScoreUtils.checkFraction(fraction)) {
            UIUtils.show(R.string.grade_edit_image_submit_error);
            return;
        }
        if (getScoreActivity().isAutomaticSubmit() && TextUtils.isEmpty(fraction)) {
            UIUtils.show(R.string.grade_score_tips);
            return;
        }
        if (getScoreActivity().isShowUnScore() && TextUtils.isEmpty(fraction)) {
            UIUtils.show(R.string.grade_score_tips);
            return;
        }
        if (ScoreUtils.scrollHasSubmit(getScoreActivity().getAnswerEntity(), fraction, pagerAdapter.cacheEmpty())) {
            nextRequest();
            return;
        }
        onScoreMoreSubmit(null, TouchMode.NEXT, fraction);
    }

    private void nextRequest() {
        netRequest(isProblem() ? GradeScoreType.PROBLEM_NEXT : UIUtils.checkNotNull(getScoreActivity()) && getScoreActivity().isShowUnScore() ? GradeScoreType.UN_NEXT : GradeScoreType.NEXT, true);
    }

    public void onFractionKeyboardChanged(@Nullable String fraction) {
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        if (TextUtils.isEmpty(fraction)) {
            changeFraction();
            return;
        }
        assert fraction != null;
        if (ScoreUtils.checkFraction(fraction)) {
            UIUtils.show(R.string.grade_edit_image_submit_error);
            return;
        }
        if (UIUtils.checkNull(getScoreActivity().getAnswerEntity())) {
            return;
        }
        if (ScoreUtils.scrollHasSubmit(getScoreActivity().getAnswerEntity(), fraction, pagerAdapter.cacheEmpty())) {
            return;
        }
        onScoreMoreSubmit(null, TouchMode.UNKNOWN, fraction);
    }

    @Override
    public void onScoreMoreSubmit(@Nullable View view, @TouchMode int position, @NonNull String info) {
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        String fraction = ScoreUtils.correctAnswerFraction(info, getScoreActivity().getMaxTopicScore());
        annotationLayout.selectAnnotationView(View.NO_ID);
        pagerAdapter.text();
        showProgress();
        ArrayList<SubmitScoreBody> objects = new ArrayList<>();
        objects.add(new SubmitScoreBody(
                getScoreActivity().getExamGroupId(),
                TextUtils.equals(fraction, UIUtils.getString(R.string.grade_unknown)) ? 1 : 0,
                getScoreActivity().getMarkingGroupId(),
                getScoreActivity().getPaperId(),
                TextUtils.equals(fraction, UIUtils.getString(R.string.grade_unknown)) ? 0 : NumberUtils.parseDouble(fraction),
                getScoreActivity().getNextStudentId(),
                getScoreActivity().getTopicId(),
                isProblem() || pagerAdapter.cacheEmpty() || pagerAdapter.getBitmap() == null ? "" : BitmapUtils.bitmapToBase64HeaderPng(pagerAdapter.getBitmap())
        ));
        onPostSubmit(objects, fraction, position);
    }

    public void changeFraction() {
        if (UIUtils.checkNotNull(getScoreActivity())) {
            getScoreActivity().onRefreshAnswerFraction();
        }
    }

    @Override
    public void onSubmitSuccess(@NonNull ScoreZipEntity entity, @NonNull String fraction, @TouchMode int type) {
        super.onSubmitSuccess(entity, fraction, type);
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        if (UIUtils.checkNotNull(getScoreActivity())) {
            getScoreActivity().onClearAnswerEditTextFocus();
        }
        ScoreEntity answerScoreEntity = getScoreActivity().getAnswerEntity();
        if (UIUtils.checkNull(answerScoreEntity)) {
            return;
        }
        answerScoreEntity.setStatus(1);
        answerScoreEntity.setIsProblem(TextUtils.equals(fraction, UIUtils.getString(R.string.grade_unknown)) ? 1 : 0);
        answerScoreEntity.getStudentPaperTopic().setIsProblem(TextUtils.equals(fraction, UIUtils.getString(R.string.grade_unknown)) ? 1 : 0);
        answerScoreEntity.setProblemStatus(isProblem() ? 1 : 0);
        answerScoreEntity.getStudentPaperTopic().setScoring(NumberUtils.parseDouble(fraction));
        pagerAdapter.removeCache();
        changeFraction();
        onAutomaticSubmit(type);
    }

    @Override
    public void onAutomaticSubmit(@TouchMode int type) {
        switch (type) {
            case TouchMode.NEXT:
                nextRequest();
                break;
            case TouchMode.PREV:
                prevRequest();
                break;
            case TouchMode.UNKNOWN:
                if (UIUtils.checkNotNull(getScoreActivity()) && !getScoreActivity().isAutomaticSubmit()) {
                    return;
                }
                nextRequest();
                break;
        }
    }

    @Override
    public void showFractionMore() {
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        KeyboardEntity keyboardEntity = GreenDaoManager.queryKeyboardV1(getScoreActivity().getMarkingGroupId());
        if (UIUtils.checkNull(keyboardEntity)) {
            return;
        }
        AnswerScoreMoreDialog.shows(getChildFragmentManager(), keyboardEntity.getTopicScore(), keyboardEntity.getHasDecimal());
    }

    @Override
    public void openAnnotation() {
        mPresenter.onAnnotationRequest();
    }

    @Override
    public void openAnnotationDelete() {
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        pagerAdapter.clearImage();
    }

    @Override
    public void openAnnotationScribble() {
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        pagerAdapter.paint();
    }

    @Override
    public void openAnnotationText() {
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        pagerAdapter.text();
        MDialogUtils.editTextInput(mActivity, (dialog, input) -> {
            annotationLayout.selectAnnotationView(R.id.score_annotation_text);
            pagerAdapter.setText(input.toString());
        });
    }

    @Override
    public void openAnnotationUndo() {
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        pagerAdapter.lastImage();
    }

    @Override
    public boolean hasNewMark() {
        return false;
    }

    @Override
    public void openAnnotationReduction() {
        if (UIUtils.checkNull(getScoreActivity())) {
            return;
        }
        if (UIUtils.checkNull(getScoreActivity().getAnswerEntity())) {
            return;
        }
        if (UIUtils.checkNull(getScoreActivity().getAnswerEntity().getStudentPaperTopic())) {
            return;
        }
        mPresenter.onScoreResetImage(getScoreActivity().getAnswerEntity().getStudentPaperTopic().getId());
    }

    @Override
    public void onAnnotationSuccess(@NonNull List<AnnotationEntity> entities) {
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        XRecyclerViewAdapter<AnnotationEntity> annotationAdapter = annotationLayout.getAnnotationAdapter();
        if (UIUtils.isEmpty(entities) || UIUtils.checkNull(annotationAdapter)) {
            annotationLayout.selectAnnotationView(View.NO_ID);
            MDialogUtils.annotationEmpty(mActivity, (dialog, which) -> UIUtils.startActivity(NoteActivity.class));
            return;
        }
        annotationAdapter.removeAll();
        annotationAdapter.addAllData(entities);
        MDialogUtils.scoreAnnotation(mActivity, annotationAdapter, (dialog, which) -> {
                    String temp = "";
                    for (AnnotationEntity annotationEntity : annotationAdapter.getData()) {
                        if (annotationEntity.isChecked()) {
                            temp = annotationEntity.getMarkingLabel();
                        }
                    }
                    if (TextUtils.isEmpty(temp)) {
                        UIUtils.show(R.string.grade_edit_image_text_empty);
                        return;
                    }
                    pagerAdapter.setText(temp);
                    annotationLayout.selectAnnotationView(R.id.score_annotation_text);
                },
                (dialog, which) -> UIUtils.startActivity(NoteActivity.class));
    }

    @Override
    public void onResetImageSuccess(@NonNull File file) {
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        annotationLayout.selectAnnotationView(View.NO_ID);
        pagerAdapter.displayFile(file);
    }

    @Override
    public void onResetImageError() {
        UIUtils.show(R.string.grade_edit_image_reset_error);
    }

    @Override
    public void onIssues() {
        if (!mStatusLayout.getStatus().equals(Status.SUCCESS)) {
            return;
        }
        onScoreMoreSubmit(null, TouchMode.UNKNOWN, UIUtils.getString(R.string.grade_unknown));
    }

    @Override
    public void openAnnotationUnFold() {
        if (UIUtils.checkNull(pagerAdapter)) {
            return;
        }
        annotationLayout.changedAnnotationUnFold();
        pagerAdapter.quiteEdit();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.grade_fragment_answer_score;
    }

    @Deprecated
    @Override
    public boolean hasSubmit() {
        return UIUtils.checkNotNull(getScoreActivity()) && TextUtils.isEmpty(getScoreActivity().getAnswerFraction());
    }

    @QuestionType
    @Override
    public int getScoreType() {
        return QuestionType.ANSWER;
    }

    @Override
    public void onLastImageEmpty() {
    }

    @Override
    public void onLastCacheMax() {
    }

    @Override
    public void onDeleteText() {
        annotationLayout.selectAnnotationView(View.NO_ID);
    }
}
