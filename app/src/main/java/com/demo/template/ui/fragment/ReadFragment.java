package com.demo.template.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.demo.template.R;
import com.demo.template.mvp.presenter.impl.ReadPresenterImpl;
import com.demo.template.mvp.view.ReadView;
import com.demo.template.ui.adapter.ReadAdapter;
import com.android.library.bridge.core.MVPFragment;

import butterknife.BindView;

/**
 * @Author xcl
 * @CreateDate 2019/5/30
 */
public class ReadFragment extends MVPFragment<ReadPresenterImpl, Object> implements ReadView {


    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPaper;

    public static ReadFragment newInstance() {
        return new ReadFragment();
    }

    @Override
    protected void initCreate(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ReadAdapter mAdapter = new ReadAdapter(getChildFragmentManager());
        viewPaper.setOffscreenPageLimit(mAdapter.getCount());
        viewPaper.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPaper);
    }

    @Override
    protected ReadPresenterImpl initPresenter() {
        return new ReadPresenterImpl(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_read;
    }
}
