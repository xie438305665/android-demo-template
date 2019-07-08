package com.android.library.widget.custom;

import android.annotation.SuppressLint;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("CommitTransaction")
public abstract class CustomFragmentPagerAdapter extends PagerAdapter {

    private final FragmentManager mFragmentManager;
    private FragmentTransaction mCurTransaction = null;
    private Fragment mCurrentPrimaryItem = null;

    public CustomFragmentPagerAdapter(@NonNull FragmentManager fm) {
        mFragmentManager = fm;
    }

    @NonNull
    public abstract Fragment getItem(int position);

    @Override
    public void startUpdate(@NonNull ViewGroup container) {
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        mCurTransaction = getBeginTransaction();
        Fragment fragment = getItem(position);
        mCurTransaction.add(container.getId(), fragment);
        if (fragment != mCurrentPrimaryItem) {
            fragment.setMenuVisibility(false);
            fragment.setUserVisibleHint(false);
        }
        return fragment;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        mCurTransaction = getBeginTransaction();
        Fragment fragment = (Fragment) object;
        mCurTransaction.remove(fragment);
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Fragment fragment = (Fragment) object;
        if (fragment != mCurrentPrimaryItem) {
            if (mCurrentPrimaryItem != null) {
                mCurrentPrimaryItem.setMenuVisibility(false);
                mCurrentPrimaryItem.setUserVisibleHint(false);
            }
            fragment.setMenuVisibility(true);
            fragment.setUserVisibleHint(true);
            mCurrentPrimaryItem = fragment;
        }
    }

    @Override
    public void finishUpdate(@NonNull ViewGroup container) {
        if (mCurTransaction != null) {
            mCurTransaction.commitAllowingStateLoss();
            mCurTransaction = null;
            mFragmentManager.executePendingTransactions();
        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return ((Fragment) object).getView() == view;
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    private FragmentTransaction getBeginTransaction() {
        if (mCurTransaction == null) {
            mCurTransaction = mFragmentManager.beginTransaction();
        }
        return mCurTransaction;
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public <T extends Fragment> T currentItem() {
        return (T) this.mCurrentPrimaryItem;
    }
}
