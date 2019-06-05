package com.demo.template.ui.template.common.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.android.library.bridge.core.StatusDialogFragment;
import com.android.library.bridge.util.UIUtils;
import com.demo.template.R;
import com.demo.template.annotation.BundleKey;
import com.demo.template.annotation.TouchMode;
import com.demo.template.factory.ScoreKeyboardFactory;
import com.xadapter.adapter.XRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.OnClick;

public class AnswerScoreMoreDialog extends StatusDialogFragment {

    @BindView(R.id.dialog_answer_more_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.dialog_answer_score_more_left_tv)
    AppCompatTextView mLeftTv;
    @BindView(R.id.dialog_answer_score_more_center_tv)
    AppCompatTextView mCenterTv;
    @BindView(R.id.dialog_answer_score_more_right_tv)
    AppCompatTextView mRightTv;
    @BindView(R.id.dialog_answer_bottom_view)
    FrameLayout frameLayout;

    private XRecyclerViewAdapter<String> mAdapter;

    private double count;
    private boolean showDouble;

    public static void shows(@NonNull FragmentManager fragmentManager, double count, boolean showDouble) {
        AnswerScoreMoreDialog dialog = new AnswerScoreMoreDialog();
        Bundle bundle = new Bundle();
        bundle.putDouble(BundleKey.FRACTION_MORE_COUNT, count);
        bundle.putBoolean(BundleKey.FRACTION_MORE_SHOW_DOUBLE, showDouble);
        dialog.setArguments(bundle);
        dialog.show(fragmentManager, AnswerScoreMoreDialog.class.getSimpleName());
    }

    @OnClick({R.id.dialog_finish,
            R.id.dialog_answer_score_more_left_tv,
            R.id.dialog_answer_score_more_center_tv,
            R.id.dialog_answer_score_more_right_tv})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.dialog_finish) {
            dismiss();
        } else if (i == R.id.dialog_answer_score_more_left_tv) {
            mAdapter.removeAll();
            mAdapter.addAllData(ScoreKeyboardFactory.getAnswerScorePortMoreList(0, count, showDouble));
            recyclerView.smoothScrollBy(0, 0);
            changeView(R.id.dialog_answer_score_more_left_tv);
        } else if (i == R.id.dialog_answer_score_more_center_tv) {
            mAdapter.removeAll();
            mAdapter.addAllData(ScoreKeyboardFactory.getAnswerScorePortMoreList(1, count, showDouble));
            recyclerView.smoothScrollBy(0, 0);
            changeView(R.id.dialog_answer_score_more_center_tv);
        } else if (i == R.id.dialog_answer_score_more_right_tv) {
            mAdapter.removeAll();
            mAdapter.addAllData(ScoreKeyboardFactory.getAnswerScorePortMoreList(2, count, showDouble));
            recyclerView.smoothScrollBy(0, 0);
            changeView(R.id.dialog_answer_score_more_right_tv);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null) {
            return;
        }
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (attributes == null) {
            return;
        }
        attributes.width = LinearLayout.LayoutParams.MATCH_PARENT;
        attributes.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        attributes.gravity = Gravity.BOTTOM;
        window.setAttributes(attributes);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        count = bundle.getDouble(BundleKey.FRACTION_MORE_COUNT, 0);
        showDouble = bundle.getBoolean(BundleKey.FRACTION_MORE_SHOW_DOUBLE, false);
        changeView(R.id.dialog_answer_score_more_left_tv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(mActivity, UIUtils.isLandscape(mActivity) ? 8 : 5));
        mAdapter = (XRecyclerViewAdapter<String>) new XRecyclerViewAdapter<String>()
                .initXData(ScoreKeyboardFactory.getAnswerScorePortMoreList(0, count, showDouble))
                .setLayoutId(R.layout.grade_item_dialog_answer_score)
                .setOnItemClickListener((view, position, info) -> {
                    if (getParentFragment() instanceof ScoreMoreListener) {
                        ((ScoreMoreListener) getParentFragment()).onScoreMoreSubmit(view, TouchMode.UNKNOWN, info);
                        dismiss();
                    }
                })
                .onXBind((holder, position, s) -> holder.setTextView(R.id.dialog_answer_score_text, s));
        recyclerView.setAdapter(mAdapter);
        mCenterTv.setVisibility(count < 20 ? View.GONE : View.VISIBLE);
        mRightTv.setVisibility(count < 40 ? View.GONE : View.VISIBLE);
    }

    private void changeView(int id) {
        int childCount = frameLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = frameLayout.getChildAt(i);
            if (childAt == null) return;
            childAt.setSelected(id == childAt.getId());
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.grade_dialog_answer_score_more;
    }

    @Override
    protected int themeResId() {
        return R.style.BottomDialog;
    }

    @Override
    protected boolean getCancelable() {
        return true;
    }

    public interface ScoreMoreListener {
        void onScoreMoreSubmit(@Nullable View view, @TouchMode int position, @NonNull String info);
    }
}
