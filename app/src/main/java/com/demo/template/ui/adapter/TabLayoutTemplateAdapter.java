package com.demo.template.ui.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.android.library.bridge.util.UIUtils;
import com.demo.template.R;
import com.demo.template.ui.fragment.ListTemplateFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xcl
 */
public class TabLayoutTemplateAdapter extends FragmentPagerAdapter {

    private String[] tabLayout = UIUtils.getStringArray(R.array.tab_layout_title_array);
    private List<Fragment> fragmentList;

    public TabLayoutTemplateAdapter(FragmentManager fm) {
        super(fm);
        fragmentList = new ArrayList<>();
        fragmentList.add(ListTemplateFragment.newInstance());
        fragmentList.add(ListTemplateFragment.newInstance());
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

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabLayout[position];
    }
}