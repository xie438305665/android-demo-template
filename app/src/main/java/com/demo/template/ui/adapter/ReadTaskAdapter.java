package com.demo.template.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.library.bridge.util.UIUtils;
import com.demo.template.R;
import com.demo.template.ui.fragment.AgainTaskFragment;
import com.demo.template.ui.fragment.ArbitrationTaskFragment;
import com.demo.template.ui.fragment.ProblemTaskFragment;
import com.demo.template.ui.fragment.ReadTaskFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xcl
 */
public class ReadTaskAdapter extends FragmentPagerAdapter {
    private String[] tabValues = UIUtils.getStringArray(R.array.read_task_tab_layout_title_array);
    private List<Fragment> fragmentList = new ArrayList<>();

    public ReadTaskAdapter(FragmentManager fm) {
        super(fm);
        fragmentList.add(ReadTaskFragment.newInstance());
        fragmentList.add(ArbitrationTaskFragment.newInstance());
        fragmentList.add(AgainTaskFragment.newInstance());
        fragmentList.add(ProblemTaskFragment.newInstance());
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
