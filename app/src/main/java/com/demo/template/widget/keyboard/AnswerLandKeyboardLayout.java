package com.demo.template.widget.keyboard;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.android.library.bridge.annotation.TopicType;
import com.android.library.bridge.util.UIUtils;
import com.android.library.bridge.util.ViewUtils;
import com.demo.template.R;
import com.demo.template.annotation.ReadUIMode;
import com.demo.template.entity.ScoreMultiEntity;
import com.demo.template.factory.ScoreKeyboardFactory;
import com.demo.template.listener.action.OnAnswerLandKeyboardAction;
import com.demo.template.listener.read.IChangeViewStateListener;
import com.demo.template.widget.ScoreLayout;
import com.xadapter.adapter.XRecyclerViewAdapter;

import butterknife.BindView;

/**
 * @author y
 * @create 2019/3/21
 */
public class AnswerLandKeyboardLayout extends ScoreLayout implements IChangeViewStateListener {

    @BindView(R.id.land_fraction_view)
    RecyclerView landFractionView;

    private OnAnswerLandKeyboardAction onAnswerLandKeyboardAction;
    private XRecyclerViewAdapter<ScoreMultiEntity> landFractionAdapter;

    public AnswerLandKeyboardLayout(Context context) {
        super(context);
    }

    public AnswerLandKeyboardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnswerLandKeyboardLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        super.init();
        landFractionAdapter = (XRecyclerViewAdapter<ScoreMultiEntity>) new XRecyclerViewAdapter<ScoreMultiEntity>()
                .setLayoutId(R.layout.grade_item_answer_score_land_bottom_recycler)
                .onXBind((holder, position, scoreMultiEntity) -> holder.setTextView(R.id.answer_score_bottom_recycler_text, scoreMultiEntity.title))
                .setOnItemClickListener((view, position, info) -> {
                    if (onAnswerLandKeyboardAction != null) {
                        onAnswerLandKeyboardAction.onAnswerLandSubmit(info.title);
                    }
                });
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(new ColorDrawable(UIUtils.getColor(R.color.grade_color_keyboard_divider)));
        landFractionView.addItemDecoration(dividerItemDecoration);
        landFractionView.setLayoutManager(new LinearLayoutManager(getContext()));
        landFractionView.setHasFixedSize(true);
        landFractionView.setAdapter(landFractionAdapter);
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        boolean landscape = UIUtils.isLandscape(getContext());
        if (landscape) {
            landFractionView.smoothScrollToPosition(0);
        }
    }

    @Override
    public void onViewChangeState(@ReadUIMode int uiMode, @TopicType int type, boolean problem, boolean mixing, boolean arbitrate) {
        boolean landscape = UIUtils.isLandscape(getContext());
        if (type == TopicType.FILL) {
            ViewUtils.goneView(this);
        } else if (type == TopicType.ANSWER) {
            if (landscape) {
                ViewUtils.visibleView(this);
            } else {
                ViewUtils.goneView(this);
            }
        }
    }

    public void setOnAnswerLandKeyboardAction(@NonNull OnAnswerLandKeyboardAction onAnswerLandKeyboardAction) {
        this.onAnswerLandKeyboardAction = onAnswerLandKeyboardAction;
    }

    @Deprecated
    public void updateKeyboard(@NonNull String markingGroupId) {
        landFractionAdapter.removeAll();
        landFractionAdapter.addAllData(ScoreKeyboardFactory.getKeyboardListV1(markingGroupId));
    }

    public void updateV2Keyboard(@NonNull String topicId) {
        landFractionAdapter.removeAll();
        landFractionAdapter.addAllData(ScoreKeyboardFactory.getKeyboardListV2(topicId));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.grade_layout_answer_land_keyboard;
    }
}
