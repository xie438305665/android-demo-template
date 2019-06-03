package com.demo.template.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.demo.template.R;
import com.demo.template.ui.fragment.CompletedFragment;
import com.demo.template.ui.fragment.TODOFragment;
import com.android.library.bridge.util.UIUtils;

/**
 * @author xcl
 */
public class ReadAdapter extends FragmentPagerAdapter {
    private String[] tabValues = UIUtils.getStringArray(R.array.tab_layout_title_array);
    private static final int TODO = 0;

    public ReadAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if (i == TODO) {
            return TODOFragment.newInstance();
        }
        return CompletedFragment.newInstance();
    }

    @Override
    public int getCount() {
        return tabValues.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabValues[position];
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
