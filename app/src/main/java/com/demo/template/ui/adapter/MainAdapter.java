package com.demo.template.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.demo.template.ui.fragment.ReadFragment;
import com.demo.template.ui.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xcl
 */
public class MainAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    public MainAdapter(FragmentManager fm) {
        super(fm);
        fragmentList = new ArrayList<>();
        fragmentList.add(ReadFragment.newInstance());
        fragmentList.add(UserFragment.newInstance());
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}