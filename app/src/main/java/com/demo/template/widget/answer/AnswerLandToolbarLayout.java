package com.demo.template.widget.answer;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.android.library.bridge.annotation.TopicType;
import com.android.library.bridge.util.UIUtils;
import com.android.library.bridge.util.ViewUtils;
import com.demo.template.R;
import com.demo.template.annotation.ReadUIMode;
import com.android.library.net.entity.template.GroupQuotaEntity;
import com.android.library.net.entity.template.PairsMyProgressEntity;
import com.demo.template.listener.action.OnAnswerLandToolbarAction;
import com.demo.template.listener.read.IChangeViewStateListener;
import com.demo.template.utils.SpannableUtils;
import com.demo.template.widget.ScoreFractionEditTextLayout;
import com.demo.template.widget.ScoreLayout;
import com.demo.template.widget.ScoreProgressLayout;
import com.status.layout.Status;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author y
 * @create 2019/3/27
 */
public class AnswerLandToolbarLayout extends ScoreLayout implements IChangeViewStateListener, TextView.OnEditorActionListener, TextWatcher, View.OnFocusChangeListener {

    @BindView(R.id.score_progress_layout)
    ScoreProgressLayout progressLayout;
    @BindView(R.id.answer_land_toolbar_topic_count)
    AppCompatTextView topicCount;
    @BindView(R.id.answer_land_toolbar_percentage)
    AppCompatTextView percentage;
    @BindView(R.id.answer_land_toolbar_name)
    AppCompatTextView name;
    @BindView(R.id.answer_land_toolbar_et_fraction)
    ScoreFractionEditTextLayout editText;
    @BindView(R.id.answer_land_toolbar_max_topic_score)
    AppCompatTextView maxTopicScore;
    @BindView(R.id.answer_land_toolbar_issues)
    AppCompatImageView issuesView;
    private OnAnswerLandToolbarAction onAnswerLandToolbarAction;

    public AnswerLandToolbarLayout(Context context) {
        super(context);
    }

    public AnswerLandToolbarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnswerLandToolbarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @OnClick({R.id.answer_land_toolbar_more, R.id.answer_land_toolbar_issues, R.id.answer_land_toolbar_finish})
    public void onClicked(View view) {
        if (onAnswerLandToolbarAction == null) {
            return;
        }
        int id = view.getId();
        if (id == R.id.answer_land_toolbar_more) {
            if (!TextUtils.equals(onAnswerLandToolbarAction.getCurrentStatus(), Status.SUCCESS)) {
                return;
            }
            onAnswerLandToolbarAction.onAnswerLandToolbarOpenDrawer();
        } else if (id == R.id.answer_land_toolbar_issues) {
            onAnswerLandToolbarAction.onAnswerLandToolbarIssues();
        } else if (id == R.id.answer_land_toolbar_finish) {
            onAnswerLandToolbarAction.onAnswerLandToolbarFinish();
        }
    }

    @Override
    protected void init() {
        super.init();
        ViewUtils.goneView(progressLayout);
        progressLayout.setProgressDrawable(new ColorDrawable(UIUtils.getColor(R.color.colorPrimary)));
        editText.setOnEditorActionListener(this);
        editText.addTextChangedListener(this);
        editText.setOnFocusChangeListener(this);
    }

    public void setOnAnswerLandToolbarAction(@NonNull OnAnswerLandToolbarAction onAnswerLandToolbarAction) {
        this.onAnswerLandToolbarAction = onAnswerLandToolbarAction;
    }

    @Override
    public void onViewChangeState(@ReadUIMode int uiMode, @TopicType int type, boolean problem, boolean mixing, boolean arbitrate) {
        boolean landscape = UIUtils.isLandscape(getContext());
        switch (uiMode) {
            case ReadUIMode.INIT:
                init(type, problem, mixing, arbitrate, landscape);
                break;
            case ReadUIMode.REPLACE:
                if (type == TopicType.FILL || !landscape) {
                    ViewUtils.goneView(this);
                } else {
                    ViewUtils.visibleView(this);
                }
                break;
            case ReadUIMode.LANDSCAPE:
                if (landscape) {
                    ViewUtils.visibleView(this);
                } else {
                    ViewUtils.goneView(this);
                }
                break;
        }
    }

    private void init(@TopicType int type, boolean problem, boolean mixing, boolean arbitrate, boolean landscape) {
        if (problem) {
            ViewUtils.goneView(issuesView, progressLayout, percentage);
        } else {
            ViewUtils.visibleView(issuesView);
        }
        if (arbitrate) {
            ViewUtils.goneView(issuesView, percentage);
        }
        if (problem || !mixing || arbitrate) {
            ViewUtils.visibleView(name);
        } else {
            ViewUtils.goneView(name);
        }
        if (type == TopicType.ANSWER && landscape) {
            ViewUtils.visibleView(this);
        } else {
            ViewUtils.goneView(this);
        }
    }

    public void clearFocus() {
        editText.clearFocus();
    }

    @NonNull
    public String getLandFraction() {
        return editText.getFractionText();
    }

    public void updateAnswerMaxScore(@NonNull String score) {
        this.maxTopicScore.setText(String.format(UIUtils.getString(R.string.grade_score_max_topic), score));
    }

    public void setTitle(@NonNull String index) {
        this.topicCount.setText(String.format(UIUtils.getString(R.string.grade_score_title), index));
    }

    @Deprecated
    public void updatePercentageAndSchedule(@NonNull GroupQuotaEntity gqEntity) {
        ViewUtils.visibleView(progressLayout);
        progressLayout.setMax(gqEntity.getMarkingNum() + gqEntity.getMarkedNum());
        progressLayout.setProgressBar(gqEntity.getMarkedNum());
        progressLayout.setSchedule(String.format(UIUtils.getString(R.string.grade_score_schedule_v2), gqEntity.getMarkedNum(), gqEntity.getMarkingNum() + gqEntity.getMarkedNum()));
        this.percentage.setText(SpannableUtils.getAnswerV2Percentage(UIUtils.getString(R.string.grade_score_percentage_suffix), gqEntity.getCorrectRate()));
    }

    public void updateV2PercentageAndSchedule(@NonNull PairsMyProgressEntity gqEntity) {
        ViewUtils.visibleView(progressLayout);
        progressLayout.setMax(gqEntity.getMarkingNum() + gqEntity.getMarkedNum());
        progressLayout.setProgressBar(gqEntity.getMarkedNum());
        progressLayout.setSchedule(String.format(UIUtils.getString(R.string.grade_score_schedule_v2), gqEntity.getMarkedNum(), gqEntity.getMarkingNum() + gqEntity.getMarkedNum()));
        this.percentage.setText(SpannableUtils.getAnswerV2Percentage(UIUtils.getString(R.string.grade_score_percentage_v2), gqEntity.getAccuracyRate()).append("%"));
    }

    public void hasNewMark(boolean hasNewMark) {
        if (hasNewMark) {
            ViewUtils.goneView(percentage);
        } else {
            ViewUtils.visibleView(percentage);
        }
    }

    public void updateAnswerFraction(@NonNull String fraction) {
        String suffix = TextUtils.equals(fraction, UIUtils.getString(R.string.grade_unknown)) ? "" : "分";
        editText.setSelected(TextUtils.isEmpty(fraction));
        editText.setFractionText(TextUtils.isEmpty(fraction) ? "" : fraction + suffix);
    }

    public void updateName(@NonNull String name) {
        this.name.setText(name);
    }

    public void updateName(@NonNull Spanned name) {
        this.name.setText(name);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEND) {
            if (v.getText().toString().isEmpty()) {
                UIUtils.show(R.string.grade_answer_score_text_empty_send);
            } else {
                clearFocus();
                if (onAnswerLandToolbarAction != null) {
                    onAnswerLandToolbarAction.onAnswerLandToolbarKeyboardChanged(getLandFraction());
                }
            }
        }
        return true;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString().contains(".")) {
            if (s.length() - 1 - s.toString().indexOf(".") > 1) {
                s = s.toString().subSequence(0, s.toString().indexOf(".") + 2);
                editText.setText(s);
                editText.setSelection(s.length());
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus && editText.getText() != null && getLandFraction().contains("分")) {
            editText.setSelection(editText.getText().length() - 1);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        editText.setOnEditorActionListener(null);
        editText.addTextChangedListener(null);
        editText.setOnFocusChangeListener(null);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.grade_layout_answer_land_toolbar;
    }
}
