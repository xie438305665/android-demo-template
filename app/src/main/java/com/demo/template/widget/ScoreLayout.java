package com.demo.template.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.ButterKnife;

/**
 * @author y
 * @create 2019/3/21
 */
public abstract class ScoreLayout extends FrameLayout {

    public ScoreLayout(Context context) {
        super(context);
        init();
    }

    public ScoreLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScoreLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected void init() {
        View inflate = View.inflate(getContext(), getLayoutId(), null);
        addView(inflate, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        ButterKnife.bind(this);
    }

    protected abstract int getLayoutId();
}
