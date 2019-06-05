package com.demo.template.ui.template.newx.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.android.library.net.entity.template.ScoreTaskEntity;
import com.demo.template.entity.ScoreZipV2Entity;
import com.demo.template.ui.template.newx.fragment.BaseScoreFragment;
import com.demo.template.ui.template.newx.fragment.FillChildFragment;

import java.util.List;

/**
 * @author y
 */
public class FillScoreAdapter extends FragmentPagerAdapter {

    @Nullable
    private FillChildFragment currentFragment;

    public FillScoreAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new FillChildFragment();
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);
        currentFragment = (FillChildFragment) object;
    }

    /**
     * {@link BaseScoreFragment#onScoreFirstNetSuccess(ScoreZipV2Entity)}
     * {@link BaseScoreFragment#onScoreUnNetSuccess(ScoreZipV2Entity)}
     */
    public void onChangedUI(@Nullable List<ScoreTaskEntity> scoreEntities) {
        if (getCurrentFragment() != null && scoreEntities != null) {
            getCurrentFragment().updateUI(scoreEntities);
        }
    }

    @Nullable
    public FillChildFragment getCurrentFragment() {
        return currentFragment;
    }

    @Nullable
    public List<ScoreTaskEntity> getCurrentData() {
        if (getCurrentFragment() == null) {
            return null;
        }
        return getCurrentFragment().getData();
    }

    @Nullable
    public ScoreTaskEntity getSubmitEntity() {
        if (getCurrentFragment() == null) {
            return null;
        }
        return getCurrentFragment().getSubmitEntity();
    }

    public int getSubmitEntityPosition() {
        if (getCurrentFragment() == null) {
            return -1;
        }
        return getCurrentFragment().getSubmitEntityPosition();
    }

    public void refresh() {
        if (getCurrentFragment() != null) {
            getCurrentFragment().refresh();
        }
    }

    public void notifyDataSetChanged() {
        if (getCurrentFragment() != null) {
            getCurrentFragment().notifyDataSetChanged();
        }
    }

    public void smoothScrollToPosition(int position) {
        if (getCurrentFragment() != null) {
            getCurrentFragment().smoothScrollToPosition(position);
        }
    }

    public boolean hasSubmit() {
        if (getCurrentFragment() != null) {
            return getCurrentFragment().hasSubmit();
        }
        return false;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
}
