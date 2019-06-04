package com.demo.template.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.RelativeLayout;

import com.android.library.bridge.core.MVPActivity;
import com.android.library.bridge.core.base.IPresenter;
import com.android.library.bridge.util.UIUtils;
import com.android.library.widget.custom.CustomViewPager;
import com.demo.template.R;
import com.demo.template.ui.adapter.ReadTaskAdapter;
import com.socks.library.KLog;

import butterknife.BindDimen;
import butterknife.BindView;

/**
 * @author xcl
 * @create 2019/6/4
 * 阅卷任务
 */
public class ReadTaskActivity extends MVPActivity {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    CustomViewPager viewPaper;

    @BindDimen(R.dimen.dp_23)
    int iconDimen;

    public static void start() {
        UIUtils.startActivity(ReadTaskActivity.class);
    }

    @Override
    protected void initCreate(@Nullable Bundle savedInstanceState) {
        ReadTaskAdapter mAdapter = new ReadTaskAdapter(getSupportFragmentManager());
        viewPaper.setOffscreenPageLimit(mAdapter.getCount());
        viewPaper.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPaper);
    }

    @Override
    protected IPresenter initPresenter() {
        return null;
    }

    @Override
    protected boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        mCustomToolBar.setCenterTvText(R.string.read_task_title);
        mCustomToolBar.getRightIv().setVisibility(View.VISIBLE);
        RelativeLayout.LayoutParams rlParams = (RelativeLayout.LayoutParams) mCustomToolBar.getRightIv().getLayoutParams();
        rlParams.width = iconDimen;
        rlParams.height = iconDimen;
        mCustomToolBar.getRightIv().setLayoutParams(rlParams);
        mCustomToolBar.setRightIvIcon(R.drawable.ic_pw_new_true);
    }

    @Override
    public void onRightClick() {
        super.onRightClick();
        KLog.d();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_read_task;
    }
}
