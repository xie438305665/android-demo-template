package com.demo.template.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.android.library.bridge.annotation.TopicType;
import com.android.library.bridge.util.UIUtils;
import com.demo.template.R;


@SuppressLint("ViewConstructor")
public class ScoreGuideView extends FrameLayout {

    @TopicType
    private int scoreType;
    private int clickCount;
    private ScoreGuideCallback callback;
    private AppCompatImageView tips;

    public ScoreGuideView(@NonNull Context context, @TopicType int scoreType, ScoreGuideCallback scoreGuideCallback) {
        super(context);
        this.scoreType = scoreType;
        this.callback = scoreGuideCallback;
        init();
    }

    private void init() {
        View inflate = View.inflate(getContext(), R.layout.grade_layout_score_guide, null);
        tips = inflate.findViewById(R.id.score_guide_tips);
        View ok = inflate.findViewById(R.id.score_guide_ok);
        addView(inflate);
        ok.setOnClickListener(v -> click(scoreType));
        ok.performClick();
    }

    public void click(@TopicType int scoreType) {
        this.scoreType = scoreType;
        if (clickCount == 3) {
            setVisibility(GONE);
            clickCount = 0;
            callback.gone();
            return;
        }
        updateTipsImg();
        clickCount += 1;
    }

    private void updateTipsImg() {
        if (scoreType == TopicType.FILL) {
            tips.setImageDrawable(clickCount == 0 ? getResources().getDrawable(R.drawable.grade_ic_score_guide_minor_setting) :
                    clickCount == 1 ? getResources().getDrawable(R.drawable.grade_ic_score_guide_keyboard_setting)
                            : getResources().getDrawable(R.drawable.grade_ic_score_guide_switch));
        } else {
            tips.setImageDrawable(clickCount == 0 ? UIUtils.getDrawable(R.drawable.grade_ic_score_guide_big_question_setting) :
                    clickCount == 1 ? getResources().getDrawable(R.drawable.grade_ic_score_guide_switch)
                            : getResources().getDrawable(R.drawable.grade_ic_score_guide_zoom));
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    public interface ScoreGuideCallback {
        void gone();
    }
}
