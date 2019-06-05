package com.demo.template.ui.read.oldx.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.library.bridge.util.UIUtils;
import com.android.library.widget.custom.CustomViewPager;
import com.demo.template.ui.read.common.activity.ScoreGuideActivity;
import com.demo.template.ui.read.oldx.adapter.OldScoreAdapter;
import com.demo.template.ui.read.oldx.impl.OldScorePresenter;

/**
 * @author y
 * @create 2019/4/4
 */
@Deprecated
public abstract class OldScoreViewPagerActivity extends ScoreGuideActivity<OldScorePresenter> implements CustomViewPager.OnScrollOrientationListener {

    @Nullable
    private OldScoreAdapter scoreAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        scoreAdapter = new OldScoreAdapter(getSupportFragmentManager());
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initCreate(@Nullable Bundle savedInstanceState) {
        super.initCreate(savedInstanceState);
        viewPager.setAdapter(scoreAdapter);
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

    @Nullable
    public OldScoreAdapter getScoreAdapter() {
        return scoreAdapter;
    }

    public void setScoreAdapter(@NonNull OldScoreAdapter adapter) {
        this.scoreAdapter = adapter;
    }
}
