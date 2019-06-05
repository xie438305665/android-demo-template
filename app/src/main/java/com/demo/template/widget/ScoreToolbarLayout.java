package com.demo.template.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.android.library.bridge.annotation.QuestionType;
import com.android.library.bridge.util.UIUtils;
import com.android.library.bridge.util.ViewUtils;
import com.demo.template.R;
import com.demo.template.annotation.ReadUIMode;
import com.demo.template.listener.action.OnScoreToolbarAction;
import com.demo.template.listener.read.IChangeViewStateListener;
import com.status.layout.Status;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author y
 * @create 2019/3/27
 */
public class ScoreToolbarLayout extends ScoreLayout implements IChangeViewStateListener {

    @BindView(R.id.score_toolbar_title)
    AppCompatTextView title;
    private OnScoreToolbarAction onScoreToolbarAction;

    public ScoreToolbarLayout(Context context) {
        super(context);
    }

    public ScoreToolbarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScoreToolbarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @OnClick({R.id.score_toolbar_finish, R.id.score_toolbar_more})
    public void onClicked(View view) {
        if (onScoreToolbarAction == null) {
            return;
        }
        int id = view.getId();
        if (id == R.id.score_toolbar_finish) {
            onScoreToolbarAction.onScoreToolbarFinish();
        } else if (id == R.id.score_toolbar_more) {
            if (!TextUtils.equals(onScoreToolbarAction.getCurrentStatus(), Status.SUCCESS)) {
                return;
            }
            onScoreToolbarAction.onScoreToolbarOpenDrawer();
        }
    }

    @Override
    protected void init() {
        super.init();
        setTitle(String.format(UIUtils.getString(R.string.grade_score_title), "0"));
    }

    @Override
    public void onViewChangeState(@ReadUIMode int uiMode, @QuestionType int type, boolean problem, boolean mixing, boolean arbitrate) {
        boolean landscape = UIUtils.isLandscape(getContext());
        if (type == QuestionType.FILL) {
            ViewUtils.visibleView(this);
        } else if (type == QuestionType.ANSWER) {
            if (landscape) {
                ViewUtils.goneView(this);
            } else {
                ViewUtils.visibleView(this);
            }
        }
    }

    public void setOnScoreToolbarAction(@NonNull OnScoreToolbarAction onScoreToolbarAction) {
        this.onScoreToolbarAction = onScoreToolbarAction;
    }

    public void setTitle(@NonNull String title) {
        this.title.setText(title);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.grade_layout_score_toolbar;
    }
}
