package com.demo.template.ui.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.library.bridge.R2;
import com.google.android.material.tabs.TabLayout;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.library.bridge.core.MVPFragment;
import com.android.library.widget.custom.CustomViewPager;
import com.demo.template.R;
import com.demo.template.mvp.presenter.impl.TabLayoutTemplatePresenterImpl;
import com.demo.template.mvp.view.TabLayoutTemplateView;
import com.demo.template.ui.adapter.TabLayoutTemplateAdapter;

import butterknife.BindView;

/**
 * @Author xcl
 * @CreateDate 2019/5/30
 */
public class TabLayoutTemplateFragment extends MVPFragment<TabLayoutTemplatePresenterImpl, Object> implements TabLayoutTemplateView {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    CustomViewPager viewPaper;

    public static TabLayoutTemplateFragment newInstance() {
        return new TabLayoutTemplateFragment();
    }

    @Override
    protected void initCreate(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TabLayoutTemplateAdapter mAdapter = new TabLayoutTemplateAdapter(getChildFragmentManager());
        viewPaper.setOffscreenPageLimit(mAdapter.getCount());
        viewPaper.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPaper);
    }

    @Override
    protected TabLayoutTemplatePresenterImpl initPresenter() {
        return new TabLayoutTemplatePresenterImpl(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab_layout;
    }
}
