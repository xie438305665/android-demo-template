package com.demo.template.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.library.bridge.util.UIUtils;
import com.demo.template.R;
import com.demo.template.ui.fragment.CompletedFragment;
import com.demo.template.ui.fragment.TODOFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xcl
 */
public class ReadAdapter extends FragmentPagerAdapter {
    private String[] tabValues = UIUtils.getStringArray(R.array.read_tab_layout_title_array);
    private List<Fragment> fragmentList = new ArrayList<>();

    public ReadAdapter(FragmentManager fm) {
        super(fm);
        fragmentList.add(TODOFragment.newInstance());
        fragmentList.add(CompletedFragment.newInstance());
    }

    @Override
    public Fragment getItem(int i) {
        if (UIUtils.isEmpty(fragmentList)) return null;
        return fragmentList.get(i);
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
