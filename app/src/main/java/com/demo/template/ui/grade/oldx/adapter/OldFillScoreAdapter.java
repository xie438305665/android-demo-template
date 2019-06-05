package com.demo.template.ui.grade.oldx.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.android.library.net.entity.template.ScoreEntity;
import com.demo.template.entity.ScoreZipEntity;
import com.demo.template.ui.grade.oldx.fragment.OldBaseScoreFragment;
import com.demo.template.ui.grade.oldx.fragment.OldFillChildFragment;

import java.util.List;

/**
 * @author y
 */
@Deprecated
public class OldFillScoreAdapter extends FragmentPagerAdapter {

    @Nullable
    private OldFillChildFragment currentFragment;

    public OldFillScoreAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new OldFillChildFragment();
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);
        currentFragment = (OldFillChildFragment) object;
    }

    /**
     * {@link OldBaseScoreFragment#onScoreFirstNetSuccess(ScoreZipEntity)}
     * {@link OldBaseScoreFragment#onScoreUnNetSuccess(ScoreZipEntity)}
     */
    public void onChangedUI(@Nullable List<ScoreEntity> scoreEntities) {
        if (getCurrentFragment() != null && scoreEntities != null) {
            getCurrentFragment().updateUI(scoreEntities);
        }
    }

    @Nullable
    public OldFillChildFragment getCurrentFragment() {
        return currentFragment;
    }

    @Nullable
    public List<ScoreEntity> getCurrentData() {
        if (getCurrentFragment() == null) {
            return null;
        }
        return getCurrentFragment().getData();
    }

    @Nullable
    public ScoreEntity getSubmitEntity() {
        if (getCurrentFragment() == null) {
            return null;
        }
        return getCurrentFragment().getSubmitEntity();
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
