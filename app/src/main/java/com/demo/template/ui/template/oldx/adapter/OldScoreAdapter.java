package com.demo.template.ui.template.oldx.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.android.library.bridge.util.UIUtils;
import com.android.library.widget.custom.CustomFragmentPagerAdapter;
import com.demo.template.annotation.DrawerPosition;
import com.demo.template.annotation.TouchMode;
import com.demo.template.listener.read.OldOnScoreDataOwnListener;
import com.demo.template.ui.template.oldx.fragment.OldAnswerScoreFragment;
import com.demo.template.ui.template.oldx.fragment.OldBaseScoreFragment;
import com.demo.template.ui.template.oldx.fragment.OldFillScoreFragment;
import com.demo.template.ui.template.oldx.fragment.OldScoreBridgeFragment;

/**
 * @author y
 * @create 2018/11/22
 */
@Deprecated
public class OldScoreAdapter extends CustomFragmentPagerAdapter {

    @Nullable
    private OldOnScoreDataOwnListener onScoreDataOwnListener;

    public OldScoreAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public void setOnScoreDataOwnListener(@NonNull OldOnScoreDataOwnListener onScoreDataOwnListener) {
        this.onScoreDataOwnListener = onScoreDataOwnListener;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new OldScoreBridgeFragment();
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    /**
     * 解答题更多分
     */
    public void showAnswerFractionMore() {
        if (UIUtils.checkNull(getAnswerFragment())) {
            return;
        }
        getAnswerFragment().showFractionMore();
    }

    /**
     * 解答题竖屏键盘打分
     *
     * @param fraction 分数
     */
    public void onAnswerScoreSubmit(@NonNull String fraction) {
        if (UIUtils.checkNull(getAnswerFragment())) {
            return;
        }
        getAnswerFragment().onScoreMoreSubmit(null, TouchMode.UNKNOWN, fraction);
    }

    /**
     * 获取当前中介{@link OldBaseScoreFragment}
     *
     * @return {@link OldBaseScoreFragment}
     */
    @Nullable
    public OldBaseScoreFragment getCurrentFragment() {
        OldScoreBridgeFragment fragment = currentItem();
        if (UIUtils.checkNull(fragment)) {
            return null;
        }
        return fragment.bride();
    }

    /**
     * 获取当前解答题{@link OldAnswerScoreFragment}
     *
     * @return {@link OldAnswerScoreFragment}
     */
    @Nullable
    public OldAnswerScoreFragment getAnswerFragment() {
        OldBaseScoreFragment scoreFragment = getCurrentFragment();
        if (UIUtils.checkNull(scoreFragment) || scoreFragment instanceof OldFillScoreFragment) {
            return null;
        }
        return (OldAnswerScoreFragment) scoreFragment;
    }

    /**
     * 获取当前填空题{@link OldFillScoreFragment}
     *
     * @return {@link OldFillScoreFragment}
     */
    @Nullable
    public OldFillScoreFragment getFillFragment() {
        OldBaseScoreFragment scoreFragment = getCurrentFragment();
        if (UIUtils.checkNull(scoreFragment) || scoreFragment instanceof OldAnswerScoreFragment) {
            return null;
        }
        return (OldFillScoreFragment) scoreFragment;
    }

    /**
     * 填空题当前页所有试题全错
     */
    public void fillAllFalse() {
        if (UIUtils.checkNull(getFillFragment())) {
            return;
        }
        getFillFragment().onSubmitAll(false);
    }

    /**
     * 填空题当前页所有试题全对
     */
    public void fillAllTrue() {
        if (UIUtils.checkNull(getFillFragment())) {
            return;
        }
        getFillFragment().onSubmitAll(true);
    }

    /**
     * 填空题提交键盘分数
     *
     * @param fraction 分数
     */
    public void fillSubmit(@NonNull String fraction) {
        if (UIUtils.checkNull(getFillFragment())) {
            return;
        }
        getFillFragment().onSubmit(fraction);
    }

    /**
     * 解答题问题卷
     */
    public void answerIssues() {
        if (UIUtils.checkNull(getAnswerFragment())) {
            return;
        }
        getAnswerFragment().onIssues();
    }

    /**
     * 解答题横屏输入打分改变
     *
     * @param landFraction 分数
     */
    public void onAnswerKeyboardChanged(@Nullable String landFraction) {
        OldAnswerScoreFragment answerFragment = getAnswerFragment();
        if (UIUtils.checkNull(answerFragment)) {
            return;
        }
        answerFragment.onFractionKeyboardChanged(landFraction);
    }

    /**
     * 解答题横屏输入打分
     *
     * @param fraction 分数
     */
    public void onAnswerLandSubmit(@NonNull String fraction) {
        OldAnswerScoreFragment answerFragment = getAnswerFragment();
        if (UIUtils.checkNull(answerFragment)) {
            return;
        }
        answerFragment.onScoreMoreSubmit(null, TouchMode.UNKNOWN, fraction);
    }

    /**
     * 侧边栏填空题点击事件
     *
     * @param position 位置 {@link DrawerPosition}
     */
    public void onFillScoreDrawerItemClick(@DrawerPosition int position) {
        OldFillScoreFragment fillFragment = getFillFragment();
        if (UIUtils.checkNull(fillFragment)) {
            return;
        }
        switch (position) {
            case DrawerPosition.QUESTIONS_DETAIL:
                if (onScoreDataOwnListener != null) {
                    onScoreDataOwnListener.openTopic();
                }
                break;
            case DrawerPosition.FILL_NUM:
                fillFragment.onShowItem();
                break;
            case DrawerPosition.KEYBOARD:
                if (onScoreDataOwnListener != null) {
                    onScoreDataOwnListener.openKeyboard();
                }
                break;
            case DrawerPosition.AUTOMATIC_SUBMIT:
                break;
            case DrawerPosition.LAND:
                break;
            case DrawerPosition.SETTING:
                break;
            case DrawerPosition.UN_SCORE:
                break;
        }
    }

    /**
     * 侧边栏解答题点击事件
     *
     * @param position 位置 {@link DrawerPosition}
     */
    public void onAnswerScoreDrawerItemClick(@DrawerPosition int position) {
        OldAnswerScoreFragment answerFragment = getAnswerFragment();
        if (UIUtils.checkNull(answerFragment)) {
            return;
        }
        switch (position) {
            case DrawerPosition.QUESTIONS_DETAIL:
                if (onScoreDataOwnListener != null) {
                    onScoreDataOwnListener.openTopic();
                }
                break;
            case DrawerPosition.KEYBOARD:
                if (onScoreDataOwnListener != null) {
                    onScoreDataOwnListener.openKeyboard();
                }
                break;
            case DrawerPosition.AUTOMATIC_SUBMIT:
                break;
            case DrawerPosition.FILL_NUM:
                break;
            case DrawerPosition.LAND:
                break;
            case DrawerPosition.SETTING:
                break;
            case DrawerPosition.UN_SCORE:
                break;
        }
    }


    public void onShowUnAnswerScore() {
        if (getCurrentFragment() != null) {
            getCurrentFragment().onShowUnAnswerScore();
        }
    }

    /**
     * 向左滑动
     */
    public void onScrollLeft() {
        if (getCurrentFragment() == null) {
            return;
        }
        getCurrentFragment().onScrollLeft();
    }

    /**
     * 向右滑动
     */
    public void onScrollRight() {
        if (getCurrentFragment() == null) {
            return;
        }
        getCurrentFragment().onScrollRight();
    }

    /**
     * 禁止缓存
     */
    public void onRemoveFragment() {
        OldScoreBridgeFragment bridgeFragment = currentItem();
        if (bridgeFragment != null) {
            bridgeFragment.onDestroyChildFragment();
        }
    }
}
