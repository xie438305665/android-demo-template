package com.demo.template.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.android.library.bridge.annotation.QuestionType;
import com.android.library.bridge.util.UIUtils;
import com.android.library.bridge.util.ViewUtils;
import com.demo.template.R;
import com.demo.template.annotation.ReadUIMode;
import com.android.library.net.entity.template.GroupQuotaEntity;
import com.android.library.net.entity.template.PairsMyProgressEntity;
import com.demo.template.listener.action.OnScoreHeaderAction;
import com.demo.template.listener.read.IChangeViewStateListener;
import com.demo.template.utils.SpannableUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author y
 * @create 2019/3/27
 */
public class ScoreHeaderLayout extends ScoreLayout implements IChangeViewStateListener, TextView.OnEditorActionListener, TextWatcher, View.OnFocusChangeListener {

    @BindView(R.id.score_header_progress_layout)
    ScoreProgressLayout progressLayout;
    @BindView(R.id.score_header_percentage)
    AppCompatTextView percentage;
    @BindView(R.id.answer_header_name)
    AppCompatTextView name;
    @BindView(R.id.answer_header_arbitration_name)
    AppCompatTextView arbitrationName;
    @BindView(R.id.answer_header_et_fraction)
    ScoreFractionEditTextLayout editText;
    @BindView(R.id.answer_header_max_topic_score)
    AppCompatTextView maxTopicScore;

    @BindView(R.id.score_header_all_false)
    View allFalse;
    @BindView(R.id.score_header_all_true)
    View allTrue;
    @BindView(R.id.answer_header_line)
    View answerHeaderLine;
    @BindView(R.id.answer_header_title)
    View answerHeaderTitle;
    private OnScoreHeaderAction onScoreHeaderAction;

    public ScoreHeaderLayout(Context context) {
        super(context);
    }

    public ScoreHeaderLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScoreHeaderLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @OnClick({R.id.score_header_all_false, R.id.score_header_all_true, R.id.score_header_answer})
    public void onClicked(View view) {
        if (onScoreHeaderAction == null) {
            return;
        }
        int id = view.getId();
        if (id == R.id.score_header_all_false) {
            onScoreHeaderAction.onScoreHeaderAllFalse();
        } else if (id == R.id.score_header_all_true) {
            onScoreHeaderAction.onScoreHeaderAllTrue();
        } else if (id == R.id.score_header_answer) {
            onScoreHeaderAction.onScoreHeaderAnswer();
        }
    }

    public void setOnScoreHeaderAction(@NonNull OnScoreHeaderAction onScoreHeaderAction) {
        this.onScoreHeaderAction = onScoreHeaderAction;
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

    @Override
    public void onViewChangeState(@ReadUIMode int uiMode, @QuestionType int type, boolean problem, boolean mixing, boolean arbitrate) {
        boolean landscape = UIUtils.isLandscape(getContext());
        if (problem || !mixing) {
            ViewUtils.visibleView(name);
        } else {
            ViewUtils.goneView(name);
        }
        if (arbitrate) {
            ViewUtils.goneView(percentage);
        }
        if (type == QuestionType.FILL) {
            ViewUtils.visibleView(this);
        } else if (type == QuestionType.ANSWER) {
            if (landscape) {
                ViewUtils.goneView(this);
            } else {
                ViewUtils.visibleView(this);
            }
        }
        if (problem) {
            onChangedProblemUI(type);
        } else {
            onChangedDefaultUI(type, uiMode == ReadUIMode.REPLACE);
        }
        if (arbitrate && type == QuestionType.FILL) {
            ViewUtils.goneView(arbitrationName);
        } else if (arbitrate && type == QuestionType.ANSWER) {
            ViewUtils.visibleView(arbitrationName);
        }
    }

    public void onChangedDefaultUI(@QuestionType int type, boolean attach) {
        if (attach) {
            progressLayout.setProgressBar(0);
        }
        if (type == QuestionType.ANSWER) {
            ViewUtils.goneView(allTrue, allFalse);
            ViewUtils.visibleView(answerHeaderLine, name, answerHeaderTitle, maxTopicScore, editText);
        } else if (type == QuestionType.FILL) {
            ViewUtils.visibleView(allFalse, allTrue);
            ViewUtils.goneView(answerHeaderLine, name, answerHeaderTitle, maxTopicScore, editText);
        }
    }

    public void onChangedProblemUI(@QuestionType int type) {
        ViewUtils.goneView(allFalse, allTrue);
        if (type == QuestionType.ANSWER) {
            ViewUtils.visibleView(answerHeaderLine, name, answerHeaderTitle, maxTopicScore, editText);
        } else if (type == QuestionType.FILL) {
            ViewUtils.goneView(answerHeaderLine, name, answerHeaderTitle, maxTopicScore, editText);
        }
    }

    public void updateName(@NonNull String name) {
        this.name.setText(name);
    }

    public void updateName(@NonNull Spanned name) {
        this.name.setText(name);
    }

    public void updateArbitrationName(@NonNull String name) {
        this.arbitrationName.setText(name);
    }

    public void updateArbitrationName(@NonNull Spanned name) {
        this.arbitrationName.setText(name);
    }

    public void updateAnswerFraction(@NonNull String text) {
        String suffix = TextUtils.equals(text, UIUtils.getString(R.string.grade_unknown)) ? "" : "分";
        editText.setSelected(TextUtils.isEmpty(text));
        editText.setFractionText(TextUtils.isEmpty(text) ? "" : text + suffix);
    }

    @NonNull
    public String getAnswerFraction() {
        return editText.getFractionText();
    }

    public void onClearFocusAnswerFraction() {
        editText.clearFocus();
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

    public void updateAnswerMaxScore(@NonNull String text) {
        this.maxTopicScore.setText(String.format(UIUtils.getString(R.string.grade_score_max_topic), text));
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        editText.setOnEditorActionListener(null);
        editText.addTextChangedListener(null);
        editText.setOnFocusChangeListener(null);
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
        if (hasFocus && editText.getText() != null && getAnswerFraction().contains("分")) {
            editText.setSelection(editText.getText().length() - 1);
        } else {
            UIUtils.offKeyboard((EditText) v);
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEND) {
            if (v.getText().toString().isEmpty()) {
                UIUtils.show(R.string.grade_answer_score_text_empty_send);
            } else {
                onScoreHeaderAction.onScoreFractionKeyboardChanged(getAnswerFraction());
            }
        }
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.grade_layout_score_header;
    }
}
