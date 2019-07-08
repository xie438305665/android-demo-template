package com.demo.template.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.demo.template.ui.fragment.TabLayoutTemplateFragment;
import com.demo.template.ui.fragment.UserTemplateFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xcl
 */
public class MainTemplateAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    public MainTemplateAdapter(FragmentManager fm) {
        super(fm);
        fragmentList = new ArrayList<>();
        fragmentList.add(TabLayoutTemplateFragment.newInstance());
        fragmentList.add(UserTemplateFragment.newInstance());
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}