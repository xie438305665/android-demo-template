package com.android.library.widget.custom;

import android.content.Context;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.library.widget.R;
import com.android.library.widget.listener.OnCustomToolBarClickListener;

/**
 * @author xcl
 */
public class CustomToolBar extends FrameLayout {
    private AppCompatImageView leftIv;
    private AppCompatImageView rightIv;
    private AppCompatTextView centerTv;
    private AppCompatTextView rightTv;
    private RelativeLayout rootView;

    private View mShadowView;

    private OnCustomToolBarClickListener listener;

    public CustomToolBar(@NonNull Context context) {
        super(context);
        init();
    }

    public CustomToolBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomToolBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Context context = getContext();
        View inflate = LinearLayout.inflate(context, R.layout.layout_toolbar_root, null);
        rootView = inflate.findViewById(R.id.toolbar_root_view);
        mShadowView = inflate.findViewById(R.id.tool_bar_shadow);
        leftIv = inflate.findViewById(R.id.left_iv);
        rightIv = inflate.findViewById(R.id.right_iv);
        centerTv = inflate.findViewById(R.id.center_tv);
        rightTv = inflate.findViewById(R.id.right_tv);
        addView(inflate);
        leftIv.setOnClickListener(v -> {
            if (listener != null) listener.onLeftClick();
        });
        centerTv.setOnClickListener(v -> {
            if (listener != null) listener.onCenterClick();
        });
        rightIv.setOnClickListener(v -> {
            if (listener != null) listener.onRightClick();
        });
        rightTv.setOnClickListener(v -> {
            if (listener != null) listener.onRightClick();
        });
    }

    public void setListener(OnCustomToolBarClickListener listener) {
        this.listener = listener;
    }

    public void setBackgroundColor(@ColorInt int id) {
        getToolBarRootView().setBackgroundColor(id);
    }

    public void setLeftIvIcon(@DrawableRes int id) {
        leftIv.setImageResource(id);
    }

    public void setCenterTvText(@StringRes int id) {
        centerTv.setText(id);
    }

    public void setCenterTvTextColor(@ColorInt int id) {
        centerTv.setTextColor(id);
    }

    public void setCenterTvTextSize(float id) {
        centerTv.setTextSize(id);
    }

    public void setRightIvIcon(@DrawableRes int id) {
        rightIv.setImageResource(id);
    }

    public void setRightTvText(@StringRes int id) {
        rightTv.setText(id);
    }

    public void setRightTvText(String rightTvText) {
        rightTv.setText(rightTvText);
    }

    public void setRightTvTextColor(@ColorInt int id) {
        rightTv.setTextColor(id);
    }

    public void setRightTvTextSize(float id) {
        rightTv.setTextSize(id);
    }

    public void setElevation(float height) {
        ViewGroup.LayoutParams layoutParams = mShadowView.getLayoutParams();
        layoutParams.height = (int) height;
        mShadowView.setLayoutParams(layoutParams);
    }

    public AppCompatImageView getLeftIv() {
        return leftIv;
    }

    public AppCompatImageView getRightIv() {
        return rightIv;
    }

    public AppCompatTextView getCenterTv() {
        return centerTv;
    }

    public AppCompatTextView getRightTv() {
        return rightTv;
    }

    public RelativeLayout getToolBarRootView() {
        return rootView;
    }
}
