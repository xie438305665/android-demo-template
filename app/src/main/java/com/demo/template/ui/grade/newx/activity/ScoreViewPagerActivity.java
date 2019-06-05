package com.demo.template.ui.grade.newx.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.android.library.bridge.util.UIUtils;
import com.android.library.widget.custom.CustomViewPager;
import com.demo.template.ui.grade.common.activity.ScoreGuideActivity;
import com.demo.template.ui.grade.newx.adapter.ScoreAdapter;
import com.demo.template.ui.grade.newx.impl.ScorePresenter;

/**
 * @author y
 * @create 2019/4/4
 */
public abstract class ScoreViewPagerActivity extends ScoreGuideActivity<ScorePresenter>
        implements CustomViewPager.OnScrollOrientationListener, ViewPager.OnPageChangeListener {

    @Nullable
    private ScoreAdapter scoreAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        scoreAdapter = new ScoreAdapter(getSupportFragmentManager());
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initCreate(@Nullable Bundle savedInstanceState) {
        super.initCreate(savedInstanceState);
        viewPager.setAdapter(scoreAdapter);
        viewPager.addOnPageChangeListener(this);
        viewPager.setOnScrollOrientationListener(this);
    }

    @Override
    public void onScrollLeft() {
        if (UIUtils.checkNull(scoreAdapter)) {
            return;
        }
        scoreAdapter.onScrollLeft();
    }

    @Override
    public void onScrollRight() {
        if (UIUtils.checkNull(scoreAdapter)) {
            return;
        }
        scoreAdapter.onScrollRight();
    }

    @Override
    public void onPageSelected(int i) {
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }

    @Nullable
    public ScoreAdapter getScoreAdapter() {
        return scoreAdapter;
    }

    public void setScoreAdapter(@NonNull ScoreAdapter scoreAdapter) {
        this.scoreAdapter = scoreAdapter;
    }
}
