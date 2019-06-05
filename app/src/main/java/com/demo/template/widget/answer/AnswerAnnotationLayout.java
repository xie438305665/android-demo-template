package com.demo.template.widget.answer;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.library.bridge.util.UIUtils;
import com.android.library.bridge.util.ValueAnimatorUtils;
import com.android.library.bridge.util.ViewUtils;
import com.demo.template.R;
import com.android.library.net.entity.template.AnnotationEntity;
import com.demo.template.listener.action.OnAnswerAnnotationAction;
import com.demo.template.widget.ScoreLayout;
import com.xadapter.adapter.XRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author y
 * @create 2019/3/21
 */
public class AnswerAnnotationLayout extends ScoreLayout {

    @BindView(R.id.gone_score_iv_call_out)
    LinearLayout mScoreCallOut;
    @BindView(R.id.score_annotation_unfold)
    AppCompatImageView scoreUnfold;
    private XRecyclerViewAdapter<AnnotationEntity> annotationAdapter;
    private OnAnswerAnnotationAction onAnswerAnnotationAction;
    private ValueAnimatorUtils valueAnimatorUtils;
    private boolean problem;
    private boolean arbitration;

    public AnswerAnnotationLayout(Context context) {
        super(context);
    }

    public AnswerAnnotationLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnswerAnnotationLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @OnClick({
            R.id.score_annotation,
            R.id.score_annotation_delete,
            R.id.score_annotation_scribble,
            R.id.score_annotation_text,
            R.id.score_annotation_reduction,
            R.id.score_annotation_undo,
            R.id.score_annotation_unfold})
    public void onViewClicked(View view) {
        if (onAnswerAnnotationAction == null) {
            return;
        }
        int id = view.getId();
        if (id == R.id.score_annotation_reduction) {
            onAnswerAnnotationAction.openAnnotationReduction();
        } else if (id == R.id.score_annotation_undo) {
            selectAnnotationView(View.NO_ID);
            onAnswerAnnotationAction.openAnnotationUndo();
        } else if (id == R.id.score_annotation_delete) {
            selectAnnotationView(View.NO_ID);
            onAnswerAnnotationAction.openAnnotationDelete();
        } else if (id == R.id.score_annotation) {
            if (annotationAdapter == null) {
                initAnnotationAdapter();
            }
            onAnswerAnnotationAction.openAnnotation();
        } else if (id == R.id.score_annotation_scribble) {
            selectAnnotationView(R.id.score_annotation_scribble);
            onAnswerAnnotationAction.openAnnotationScribble();
        } else if (id == R.id.score_annotation_text) {
            onAnswerAnnotationAction.openAnnotationText();
        } else if (id == R.id.score_annotation_unfold) {
            if (arbitration) {
                UIUtils.show("仲裁禁止批注");
                return;
            }
            if (onAnswerAnnotationAction.hasNewMark()) {
                UIUtils.show("双评禁止批注");
                return;
            }
            onAnswerAnnotationAction.openAnnotationUnFold();
        }
    }

    public void setOnAnswerAnnotationAction(@NonNull OnAnswerAnnotationAction onAnswerAnnotationAction) {
        this.onAnswerAnnotationAction = onAnswerAnnotationAction;
    }

    public void init(boolean problem, boolean arbitration) {
        this.problem = problem;
        this.arbitration = arbitration;
        if (problem) {
            ViewUtils.goneView(this);
        }
        if (problem) {
            return;
        }
        valueAnimatorUtils = ValueAnimatorUtils
                .newInstance()
                .setDuration(500)
                .addUpdateListener(new ValueAnimatorUtils.ValueAnimatorWidthUpdateListener(mScoreCallOut))
                .addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (callOutVisible()) {
                            scoreUnfold.setImageResource(R.drawable.grade_ic_score_annotation_collapse);
                        } else {
                            scoreUnfold.setImageResource(R.drawable.grade_ic_score_annotation_unfold);
                        }
                    }
                });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (callOutVisible()) {
            ViewGroup.LayoutParams layoutParams = mScoreCallOut.getLayoutParams();
            layoutParams.width = (int) (getResources().getDisplayMetrics().widthPixels / (UIUtils.isLandscape(getContext()) ? 2 : 1.5));
            mScoreCallOut.setLayoutParams(layoutParams);
        }
    }


    public boolean callOutVisible() {
        return mScoreCallOut.getWidth() > ValueAnimatorUtils.DEFAULT_MIN_VALUE;
    }

    public void selectAnnotationView(int id) {
        if (problem) {
            return;
        }
        for (int i = 0; i < mScoreCallOut.getChildCount(); i++) {
            View childAt = mScoreCallOut.getChildAt(i);
            if (childAt == null) {
                return;
            }
            childAt.setSelected(childAt.getId() == id);
        }
    }

    public XRecyclerViewAdapter<AnnotationEntity> getAnnotationAdapter() {
        return annotationAdapter;
    }

    private void initAnnotationAdapter() {
        annotationAdapter = (XRecyclerViewAdapter<AnnotationEntity>) new XRecyclerViewAdapter<AnnotationEntity>()
                .setLayoutId(R.layout.grade_item_score_annotation)
                .setOnItemClickListener((view, position, info) -> {
                    for (AnnotationEntity entity : annotationAdapter.getData()) {
                        entity.setChecked(entity.getLabelId() == info.getLabelId());
                    }
                    annotationAdapter.notifyDataSetChanged();
                })
                .onXBind((holder, position, annotationEntity) -> {
                    holder.setTextView(R.id.tv_dialog_annotation, annotationEntity.getMarkingLabel());
                    ((AppCompatCheckBox) holder.getView(R.id.tv_dialog_annotation_cb)).setChecked(annotationEntity.isChecked());
                });
    }

    public void closeAnnotation() {
        if (problem) {
            return;
        }
        if (callOutVisible()) {
            selectAnnotationView(View.NO_ID);
            valueAnimatorUtils
                    .setIntValue(mScoreCallOut.getWidth(), 0)
                    .start();
        }
    }

    public void changedAnnotationUnFold() {
        if (problem) {
            return;
        }
        if (!callOutVisible()) {
            valueAnimatorUtils
                    .setIntValue(0, (int) (getResources().getDisplayMetrics().widthPixels / (UIUtils.isLandscape(getContext()) ? 2 : 1.5)))
                    .start();
        } else {
            closeAnnotation();
        }
        selectAnnotationView(View.NO_ID);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (valueAnimatorUtils != null) {
            valueAnimatorUtils.cancel();
            valueAnimatorUtils.removeListener();
            valueAnimatorUtils.removeAllUpdateListeners();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.grade_layout_answer_annotation;
    }
}
