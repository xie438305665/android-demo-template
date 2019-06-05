package com.demo.template.widget.keyboard;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.library.bridge.util.UIUtils;
import com.demo.template.R;
import com.demo.template.entity.ScoreMultiEntity;
import com.demo.template.factory.ScoreKeyboardFactory;
import com.demo.template.listener.action.OnAnswerPortKeyboardAction;
import com.demo.template.widget.ScoreLayout;
import com.xadapter.adapter.XRecyclerViewAdapter;
import com.xadapter.adapter.multi.MultiCallBack;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author y
 * @create 2019/3/21
 */
public class AnswerPortKeyboardLayout extends ScoreLayout {

    private static final int TYPE_HEADER = 0;
    private static final int MAX_SHOW_SCORE = 14;

    @BindView(R.id.port_fraction_view)
    RecyclerView portFractionView;
    @BindView(R.id.port_fraction_more)
    AppCompatTextView fractionMore;
    private XRecyclerViewAdapter<ScoreMultiEntity> portFractionAdapter;
    private OnAnswerPortKeyboardAction onAnswerPortKeyboardAction;

    public AnswerPortKeyboardLayout(Context context) {
        super(context);
    }

    public AnswerPortKeyboardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnswerPortKeyboardLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @OnClick({R.id.port_fraction_more})
    public void onClicked(View view) {
        if (onAnswerPortKeyboardAction == null) {
            return;
        }
        int id = view.getId();
        if (id == R.id.port_fraction_more) {
            onAnswerPortKeyboardAction.onAnswerPortMoreFraction();
        }
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (UIUtils.checkNotNull(portFractionAdapter)) {
            portFractionAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void init() {
        super.init();
        portFractionAdapter = (XRecyclerViewAdapter<ScoreMultiEntity>) new XRecyclerViewAdapter<ScoreMultiEntity>()
                .setLayoutId(R.layout.grade_item_answer_score_port_bottom_recycler)
                .onXBind((holder, position, scoreMultiEntity) -> {
                    ImageView view = holder.getView(R.id.port_fraction_issues);
                    TextView textView = holder.getView(R.id.answer_score_bottom_recycler_text);
                    view.setVisibility(scoreMultiEntity.getItemType() == TYPE_HEADER ? VISIBLE : GONE);
                    textView.setVisibility(scoreMultiEntity.getItemType() != TYPE_HEADER ? VISIBLE : GONE);
                    ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
                    layoutParams.width = portFractionView.getWidth() / 7;
                    holder.itemView.setLayoutParams(layoutParams);
                    holder.setTextView(R.id.answer_score_bottom_recycler_text, scoreMultiEntity.title);
                })
                .setOnItemClickListener((view, position, info) -> {
                    if (onAnswerPortKeyboardAction == null || (TextUtils.isEmpty(info.title) && info.getItemType() == MultiCallBack.TYPE_ITEM)) {
                        return;
                    }
                    if (info.getItemType() != MultiCallBack.TYPE_ITEM) {
                        onAnswerPortKeyboardAction.onAnswerPortIssues();
                    } else {
                        onAnswerPortKeyboardAction.onAnswerPortFractionSubmit(info.title);
                    }
                });
        portFractionView.addItemDecoration(new DividerItemDecoration());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        portFractionView.setLayoutManager(gridLayoutManager);
        portFractionView.setHasFixedSize(true);
        portFractionView.setAdapter(portFractionAdapter);
    }

    public void setOnAnswerPortKeyboardAction(@NonNull OnAnswerPortKeyboardAction onAnswerPortKeyboardAction) {
        this.onAnswerPortKeyboardAction = onAnswerPortKeyboardAction;
    }

    @Deprecated
    public void updateKeyboard(@NonNull String markingGroupId, boolean problem) {
        portFractionAdapter.removeAll();
        ArrayList<ScoreMultiEntity> keyboardList = ScoreKeyboardFactory.getKeyboardListV1(markingGroupId);
        if (!problem) {
            keyboardList.add(0, new ScoreMultiEntity(0, "", TYPE_HEADER, 0));
        }
        portFractionAdapter.addAllData(keyboardList);
        fractionMore.setEnabled(keyboardList.size() > MAX_SHOW_SCORE);
    }

    public void updateV2Keyboard(@NonNull String topicId, boolean problem) {
        portFractionAdapter.removeAll();
        ArrayList<ScoreMultiEntity> keyboardList = ScoreKeyboardFactory.getKeyboardListV2(topicId);
        if (!problem) {
            keyboardList.add(0, new ScoreMultiEntity(0, "", TYPE_HEADER, 0));
        }
        portFractionAdapter.addAllData(keyboardList);
        fractionMore.setEnabled(keyboardList.size() > MAX_SHOW_SCORE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.grade_layout_answer_port_keyboard;
    }


    private class DividerItemDecoration extends RecyclerView.ItemDecoration {

        private Drawable mDivider = new ColorDrawable(UIUtils.getColor(R.color.grade_color_keyboard_divider));
        private final Rect mBounds = new Rect();

        @Override
        public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            if (parent.getLayoutManager() != null) {
                this.drawVertical(c, parent);
                this.drawHorizontal(c, parent);
            }
        }

        private void drawVertical(Canvas canvas, RecyclerView parent) {
            canvas.save();
            int left;
            int right;
            if (parent.getClipToPadding()) {
                left = parent.getPaddingLeft();
                right = parent.getWidth() - parent.getPaddingRight();
                canvas.clipRect(left, parent.getPaddingTop(), right, parent.getHeight() - parent.getPaddingBottom());
            } else {
                left = 0;
                right = parent.getWidth();
            }
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; ++i) {
                View child = parent.getChildAt(i);
                parent.getDecoratedBoundsWithMargins(child, this.mBounds);
                int bottom = this.mBounds.bottom + Math.round(child.getTranslationY());
                int top = bottom - this.mDivider.getIntrinsicHeight();
                this.mDivider.setBounds(left, top, right, bottom);
                this.mDivider.draw(canvas);
            }
            canvas.restore();
        }

        private void drawHorizontal(Canvas canvas, RecyclerView parent) {
            canvas.save();
            int top;
            int bottom;
            if (parent.getClipToPadding()) {
                top = parent.getPaddingTop();
                bottom = parent.getHeight() - parent.getPaddingBottom();
                canvas.clipRect(parent.getPaddingLeft(), top, parent.getWidth() - parent.getPaddingRight(), bottom);
            } else {
                top = 0;
                bottom = parent.getHeight();
            }
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; ++i) {
                View child = parent.getChildAt(i);
                parent.getLayoutManager().getDecoratedBoundsWithMargins(child, this.mBounds);
                int right = this.mBounds.right + Math.round(child.getTranslationX());
                int left = right - this.mDivider.getIntrinsicWidth();
                this.mDivider.setBounds(left, top, right, bottom);
                this.mDivider.draw(canvas);
            }
            canvas.restore();
        }

        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            outRect.set(1, 1, 1, 1);
        }
    }
}
