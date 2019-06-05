package com.demo.template.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.demo.template.R;

import butterknife.BindView;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * @author y
 * @create 2019/3/27
 */
public class ScoreProgressLayout extends ScoreLayout {

    @BindView(R.id.header_score_progress)
    MaterialProgressBar progressBar;
    @BindView(R.id.header_score_schedule)
    AppCompatTextView schedule;

    public ScoreProgressLayout(Context context) {
        super(context);
    }

    public ScoreProgressLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScoreProgressLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setProgressDrawable(@Nullable Drawable drawable) {
        progressBar.setIndeterminateDrawable(drawable);
    }

    public void setProgressBar(int progressBar) {
        if (progressBar == 0) {
            schedule.setText("");
        }
        this.progressBar.setProgress(progressBar);
    }

    public void setMax(int max) {
        if (progressBar.getMax() == max) {
            return;
        }
        this.progressBar.setMax(max);
    }

    public void setSchedule(@NonNull String schedule) {
        this.schedule.setText(schedule);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.grade_layout_score_progress;
    }
}
