package com.android.library.widget.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * @author xcl
 */
public class CustomViewPager extends ViewPager {
    private boolean noScroll = true;
    private float downX;
    private float downY;

    private OnScrollOrientationListener onScrollOrientationListener;

    public CustomViewPager(@NonNull Context context) {
        super(context);
        init();
    }

    public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(getContext());
            field.set(this, scroller);
        } catch (Exception ignored) {
        }
    }

    public void setOnScrollOrientationListener(OnScrollOrientationListener onScrollOrientationListener) {
        this.onScrollOrientationListener = onScrollOrientationListener;
    }

    public void setNoScroll(boolean noScroll) {
        if (noScroll && !isFakeDragging()) {
            beginFakeDrag();
        } else if (!noScroll && isFakeDragging()) {
            endFakeDrag();
        }
        this.noScroll = noScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = x;
                downY = y;
                break;
            case MotionEvent.ACTION_UP:
                float dx = x - downX;
                float dy = y - downY;
                if (Math.abs(dx) > 8 && Math.abs(dy) > 8) {
                    int orientation = getOrientation(dx, dy);
                    switch (orientation) {
                        case 'r':
                            if (onScrollOrientationListener != null) {
                                onScrollOrientationListener.onScrollRight();
                            }
                            break;
                        case 'l':
                            if (onScrollOrientationListener != null) {
                                onScrollOrientationListener.onScrollLeft();
                            }
                            break;
                        case 't':
                            if (onScrollOrientationListener != null) {
                                onScrollOrientationListener.onScrollTop();
                            }
                            break;
                        case 'b':
                            if (onScrollOrientationListener != null) {
                                onScrollOrientationListener.onScrollBottom();
                            }
                            break;
                    }
                }
                break;
        }
        if (noScroll) {
            return false;
        }
        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean arrowScroll(int direction) {
        if (noScroll) return false;
        if (direction != FOCUS_LEFT && direction != FOCUS_RIGHT) return false;
        return super.arrowScroll(direction);
    }

    private int getOrientation(float dx, float dy) {
        if (Math.abs(dx) > Math.abs(dy)) {
            return dx > 0 ? 'r' : 'l';
        } else {
            return dy > 0 ? 'b' : 't';
        }
    }

    public interface OnScrollOrientationListener {
        default void onScrollTop() {
        }

        default void onScrollBottom() {
        }

        default void onScrollLeft() {
        }

        default void onScrollRight() {
        }
    }


    private static class FixedSpeedScroller extends Scroller {
        private int mDuration = 1500;

        public FixedSpeedScroller(Context context) {
            super(context);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            //为什么需要设置滚动时间
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy, mDuration);
        }
    }
}
