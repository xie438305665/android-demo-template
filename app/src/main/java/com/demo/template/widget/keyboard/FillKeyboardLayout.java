package com.demo.template.widget.keyboard;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.library.bridge.util.UIUtils;
import com.android.library.bridge.util.ValueAnimatorUtils;
import com.android.library.bridge.util.ViewUtils;
import com.demo.template.R;
import com.demo.template.entity.ScoreMultiEntity;
import com.demo.template.factory.ScoreKeyboardFactory;
import com.demo.template.listener.action.OnFillKeyboardAction;
import com.demo.template.widget.ScoreLayout;
import com.xadapter.adapter.XRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author y
 * @create 2019/3/21
 */
public class FillKeyboardLayout extends ScoreLayout {

    @BindView(R.id.fill_bottom_root_view)
    LinearLayout bottomRootView;
    @BindView(R.id.fill_bottom_recycler_root_view)
    RelativeLayout fractionRootView;

    @BindView(R.id.fill_bottom_recycler)
    RecyclerView fractionView;
    @BindView(R.id.fill_score_bottom_unknown)
    AppCompatImageView unknown;
    private OnFillKeyboardAction onFillKeyboardAction;
    private XRecyclerViewAdapter<ScoreMultiEntity> fractionAdapter;
    private ValueAnimatorUtils bottomValueAnimation;
    private ValueAnimatorUtils fractionValueAnimation;

    public FillKeyboardLayout(Context context) {
        super(context);
    }

    public FillKeyboardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FillKeyboardLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @OnClick({
            R.id.fill_score_bottom_right,
            R.id.fill_score_bottom_wrong,
            R.id.fill_score_bottom_unknown,
            R.id.fill_score_bottom_score,
            R.id.fill_bottom_cancel})
    public void onViewClicked(View view) {
        if (onFillKeyboardAction == null) {
            return;
        }
        int i = view.getId();
        if (i == R.id.fill_score_bottom_right) {
            onFillKeyboardAction.onFillKeyboardRight();
        } else if (i == R.id.fill_score_bottom_wrong) {
            onFillKeyboardAction.onFillKeyboardWrong();
        } else if (i == R.id.fill_score_bottom_unknown) {
            onFillKeyboardAction.onFillKeyboardUnKnown();
        } else if (i == R.id.fill_score_bottom_score) {
            bottomValueAnimation
                    .setIntValue(bottomRootView.getHeight(), 0)
                    .start();
        } else if (i == R.id.fill_bottom_cancel) {
            fractionValueAnimation
                    .setIntValue(fractionRootView.getHeight(), 0)
                    .start();
        }
    }

    @Override
    protected void init() {
        super.init();
        fractionView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        fractionView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL);
        dividerItemDecoration.setDrawable(new ColorDrawable(UIUtils.getColor(R.color.colorWhite)));
        fractionView.addItemDecoration(dividerItemDecoration);
        fractionAdapter = (XRecyclerViewAdapter<ScoreMultiEntity>) new XRecyclerViewAdapter<ScoreMultiEntity>()
                .setLayoutId(R.layout.grade_item_fill_score_bottom_recycler)
                .onXBind((holder, position, s) -> holder.setTextView(R.id.fill_score_bottom_recycler_text, s.title))
                .setOnItemClickListener((view, position, info) -> {
                    if (onFillKeyboardAction != null) {
                        onFillKeyboardAction.onFillKeyboardSubmit(info.title);
                    }
                });
        fractionView.setAdapter(fractionAdapter);
        bottomValueAnimation = ValueAnimatorUtils
                .newInstance()
                .setDuration(500)
                .addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (bottomRootView.getHeight() > ValueAnimatorUtils.DEFAULT_MIN_VALUE) {
                            return;
                        }
                        fractionValueAnimation
                                .setIntValue(0, (int) UIUtils.getDimension(R.dimen.dp_60))
                                .start();
                    }
                })
                .addUpdateListener(new ValueAnimatorUtils.ValueAnimatorHeightUpdateListener(bottomRootView));
        fractionValueAnimation = ValueAnimatorUtils
                .newInstance()
                .setDuration(500)
                .addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (fractionRootView.getHeight() > ValueAnimatorUtils.DEFAULT_MIN_VALUE) {
                            return;
                        }
                        bottomValueAnimation
                                .setIntValue(0, (int) UIUtils.getDimension(R.dimen.dp_60))
                                .start();
                    }
                })
                .addUpdateListener(new ValueAnimatorUtils.ValueAnimatorHeightUpdateListener(fractionRootView));
    }

    public void setOnFillKeyboardAction(@NonNull OnFillKeyboardAction onFillKeyboardAction) {
        this.onFillKeyboardAction = onFillKeyboardAction;
    }

    @Deprecated
    public void updateKeyboard(@NonNull String markingGroupId) {
        fractionAdapter.removeAll();
        fractionAdapter.addAllData(ScoreKeyboardFactory.getKeyboardListV1(markingGroupId));
    }

    public void updateV2Keyboard(@NonNull String topicId) {
        fractionAdapter.removeAll();
        fractionAdapter.addAllData(ScoreKeyboardFactory.getKeyboardListV2(topicId));
    }

    public void goneUnknown() {
        ViewUtils.goneView(unknown);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (bottomValueAnimation != null) {
            bottomValueAnimation.cancel();
            bottomValueAnimation.removeListener();
            bottomValueAnimation.removeAllUpdateListeners();
        }
        if (fractionValueAnimation != null) {
            fractionValueAnimation.cancel();
            fractionValueAnimation.removeListener();
            fractionValueAnimation.removeAllUpdateListeners();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.grade_layout_fill_keyboard;
    }
}
