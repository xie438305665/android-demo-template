package com.android.library.widget.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.android.library.widget.R;


/**
 * @author xcl
 */
public class CustomLoadingView extends FrameLayout {
    private View progressBoxView;
    private AppCompatTextView loadingMessage;
    private View progressRootView;

    public CustomLoadingView(@NonNull Context context) {
        super(context);
        init();
    }

    public CustomLoadingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomLoadingView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View inflate = View.inflate(getContext(), R.layout.layout_progress, null);
        loadingMessage = inflate.findViewById(R.id.progress_message);
        progressRootView = inflate.findViewById(R.id.progress_root_view);
        progressBoxView = inflate.findViewById(R.id.progress_box_view);
        addView(inflate);
    }


    public CustomLoadingView setMessage(String message) {
        loadingMessage.setText(message);
        loadingMessage.setVisibility(VISIBLE);
        return this;
    }

    public void setRootViewColor(int color) {
        progressRootView.setBackgroundColor(color);
    }

    public void setBoxViewColor(int color) {
        progressBoxView.setBackgroundColor(color);
    }

    public void show() {
        setVisibility(VISIBLE);
    }

    public void hide() {
        setVisibility(GONE);
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
