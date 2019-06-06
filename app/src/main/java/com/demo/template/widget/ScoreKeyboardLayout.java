package com.demo.template.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.android.library.bridge.annotation.TopicType;
import com.android.library.bridge.util.UIUtils;
import com.android.library.bridge.util.ValueAnimatorUtils;
import com.demo.template.R;
import com.demo.template.annotation.ReadUIMode;
import com.demo.template.listener.action.OnAnswerPortKeyboardAction;
import com.demo.template.listener.action.OnFillKeyboardAction;
import com.demo.template.listener.read.IChangeViewStateListener;
import com.demo.template.widget.keyboard.AnswerPortKeyboardLayout;
import com.demo.template.widget.keyboard.FillKeyboardLayout;

import butterknife.BindView;

/**
 * @author y
 * @create 2019/3/22
 */
public class ScoreKeyboardLayout extends ScoreLayout implements IChangeViewStateListener {

    @BindView(R.id.fill_score_root_view)
    FillKeyboardLayout fillKeyboardLayout;
    @BindView(R.id.answer_score_root_view)
    AnswerPortKeyboardLayout answerKeyboardLayout;

    private ValueAnimatorUtils fillValueAnimator;
    private ValueAnimatorUtils answerValueAnimator;

    public ScoreKeyboardLayout(Context context) {
        super(context);
    }

    public ScoreKeyboardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScoreKeyboardLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        super.init();
        fillValueAnimator = ValueAnimatorUtils
                .newInstance()
                .setDuration(500)
                .addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (fillKeyboardLayout.getHeight() < ValueAnimatorUtils.DEFAULT_MIN_VALUE) {
                            answerValueAnimator
                                    .setIntValue(0, (int) UIUtils.getDimension(R.dimen.dp_100))
                                    .start();
                        }
                    }
                })
                .addUpdateListener(new ValueAnimatorUtils.ValueAnimatorHeightUpdateListener(fillKeyboardLayout));
        answerValueAnimator = ValueAnimatorUtils
                .newInstance()
                .setDuration(500)
                .addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (answerKeyboardLayout.getHeight() < ValueAnimatorUtils.DEFAULT_MIN_VALUE) {
                            fillValueAnimator
                                    .setIntValue(0, (int) UIUtils.getDimension(R.dimen.dp_60))
                                    .start();
                        }
                    }
                })
                .addUpdateListener(new ValueAnimatorUtils.ValueAnimatorHeightUpdateListener(answerKeyboardLayout));
    }

    /**
     * fillKeyboardLayout,answerKeyboardLayout注册监听
     *
     * @param onFillKeyboardAction
     * @param onAnswerPortKeyboardAction
     */
    public void register(@NonNull OnFillKeyboardAction onFillKeyboardAction, @NonNull OnAnswerPortKeyboardAction onAnswerPortKeyboardAction) {
        fillKeyboardLayout.setOnFillKeyboardAction(onFillKeyboardAction);
        answerKeyboardLayout.setOnAnswerPortKeyboardAction(onAnswerPortKeyboardAction);
    }

    @Override
    public void onViewChangeState(@ReadUIMode int uiMode, @TopicType int type, boolean problem, boolean mixing, boolean arbitrate) {
        if (uiMode == ReadUIMode.INIT && problem || uiMode == ReadUIMode.INIT && arbitrate) {
            fillKeyboardLayout.goneUnknown();
        }
        onChangedUI(type, uiMode == ReadUIMode.REPLACE);
    }

    private void onChangedUI(@TopicType int type, boolean attach) {
        boolean landscape = UIUtils.isLandscape(getContext());
        if (type == TopicType.ANSWER && answerKeyboardLayout.getHeight() > ValueAnimatorUtils.DEFAULT_MIN_VALUE && !landscape) {
            return;
        }
        if (type == TopicType.FILL && fillKeyboardLayout.getHeight() > ValueAnimatorUtils.DEFAULT_MIN_VALUE) {
            return;
        }
        if (attach) {
            changedAttachUI(type, landscape);
            return;
        }
        switch (type) {
            case TopicType.ANSWER:
                ViewGroup.LayoutParams fillLayoutParams = fillKeyboardLayout.getLayoutParams();
                fillLayoutParams.height = 0;
                fillKeyboardLayout.setLayoutParams(fillLayoutParams);
                ViewGroup.LayoutParams layoutParams = answerKeyboardLayout.getLayoutParams();
                layoutParams.height = landscape ? 0 : (int) UIUtils.getDimension(R.dimen.dp_100);
                answerKeyboardLayout.setLayoutParams(layoutParams);
                break;
            case TopicType.FILL:
                ViewGroup.LayoutParams answerLayoutParams = answerKeyboardLayout.getLayoutParams();
                answerLayoutParams.height = 0;
                answerKeyboardLayout.setLayoutParams(answerLayoutParams);
                break;
            case TopicType.MULTIPLE_CHOICE:
                break;
            case TopicType.ELECTIVE_QUESTION:
                break;
            case TopicType.ENGLISH:
                break;
        }
    }

    @Deprecated
    public void updateKeyboard(@TopicType int type, @NonNull String markingGroupId, boolean problem) {
        if (type == TopicType.ANSWER) {
            answerKeyboardLayout.updateKeyboard(markingGroupId, problem);
        } else if (type == TopicType.FILL) {
            fillKeyboardLayout.updateKeyboard(markingGroupId);
        }
    }

    public void updateV2Keyboard(@TopicType int type, @NonNull String topicId, boolean problem) {
        if (type == TopicType.ANSWER) {
            answerKeyboardLayout.updateV2Keyboard(topicId, problem);
        } else if (type == TopicType.FILL) {
            fillKeyboardLayout.updateV2Keyboard(topicId);
        }
    }

    private void changedAttachUI(@TopicType int type, boolean landscape) {
        switch (type) {
            case TopicType.ANSWER:
                if (landscape) {
                    return;
                }
                fillValueAnimator.setIntValue(fillKeyboardLayout.getHeight(), 0).start();
                break;
            case TopicType.FILL:
                answerValueAnimator.setIntValue(answerKeyboardLayout.getHeight(), 0).start();
                break;
            case TopicType.ELECTIVE_QUESTION:
                break;
            case TopicType.ENGLISH:
                break;
            case TopicType.MULTIPLE_CHOICE:
                break;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (fillValueAnimator != null) {
            fillValueAnimator.cancel();
            fillValueAnimator.removeListener();
            fillValueAnimator.removeAllUpdateListeners();
        }
        if (answerValueAnimator != null) {
            answerValueAnimator.cancel();
            answerValueAnimator.removeListener();
            answerValueAnimator.removeAllUpdateListeners();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.grade_layout_score_keyboard;
    }
}
