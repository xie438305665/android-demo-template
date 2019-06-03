package com.android.library.bridge.core;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewStub;

import com.socks.library.KLog;
import com.status.layout.OnStatusClickListener;
import com.status.layout.Status;
import com.status.layout.StatusLayout;
import com.android.library.bridge.R;
import com.android.library.bridge.core.base.BaseActivity;
import com.android.library.bridge.util.ActivityUtils;
import com.android.library.bridge.util.StatusBarUtil;
import com.android.library.bridge.util.UIUtils;
import com.android.library.widget.custom.CustomLoadingView;
import com.android.library.widget.custom.CustomToolBar;
import com.android.library.widget.listener.OnCustomToolBarClickListener;

/**
 * @author xcl
 */
public abstract class StatusActivity extends BaseActivity implements OnStatusClickListener, OnCustomToolBarClickListener {

    protected StatusLayout mStatusLayout;
    protected Bundle bundle;
    public CustomLoadingView mProgress;
    protected CustomToolBar mCustomToolBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        StatusBarUtil.darkStyle(this, UIUtils.getColor(R.color.colorBackGround));
        super.onCreate(savedInstanceState);
        KLog.d(getClass().getSimpleName());
        ActivityUtils.addActivity(this);
        setContentView(R.layout.activity_status);
        bundle = getIntent().getExtras();
        mStatusLayout = findViewById(R.id.activity_status_layout);
        mProgress = findViewById(R.id.activity_loading);
        mProgress.setRootViewColor(UIUtils.getColor(R.color.transparent));
        mProgress.setBoxViewColor(UIUtils.getColor(R.color.transparent));
        if (showToolBar()) {
            mCustomToolBar = (CustomToolBar) ((ViewStub) findViewById(R.id.vs_tool_bar)).inflate();
            mCustomToolBar.setListener(this);
            mCustomToolBar.setBackgroundColor(UIUtils.getColor(R.color.colorWhite));
        }
        mStatusLayout.addSuccessView(getLayoutId());
        mStatusLayout.addEmptyView(R.layout.layout_empty);
        mStatusLayout.addLoadingView(R.layout.layout_loading);
        mStatusLayout.addErrorView(R.layout.layout_error);
        mStatusLayout.setStatus(Status.SUCCESS);
        mStatusLayout.setOnStatusClickListener(this);
    }

    protected abstract boolean showToolBar();

    @Override
    public void onRightClick() {
    }

    @Override
    public void onLeftClick() {
        finish();
    }

    @Override
    public void onCenterClick() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mStatusLayout != null) {
            mStatusLayout.removeAllViews();
            mStatusLayout = null;
        }
        if (mProgress != null) {
            mProgress.hide();
            mProgress.removeAllViews();
            mProgress = null;
        }
        if (mCustomToolBar != null) {
            mCustomToolBar.removeAllViews();
            mCustomToolBar = null;
        }
        ActivityUtils.removeActivity(this);
    }

    @Nullable
    public View getNorMalView() {
        return mStatusLayout.getView(Status.NORMAL);
    }

    @Nullable
    public View getEmptyView() {
        return mStatusLayout.getView(Status.EMPTY);
    }

    @Nullable
    public View getErrorView() {
        return mStatusLayout.getView(Status.ERROR);
    }

    @Nullable
    public View getLoadingView() {
        return mStatusLayout.getView(Status.LOADING);
    }

    @Nullable
    public View getSuccessView() {
        return mStatusLayout.getView(Status.SUCCESS);
    }

    @Override
    public void onNorMalClick(@NonNull View view) {

    }

    @Override
    public void onLoadingClick(@NonNull View view) {

    }

    @Override
    public void onEmptyClick(@NonNull View view) {

    }

    @Override
    public void onSuccessClick(@NonNull View view) {

    }

    @Override
    public void onErrorClick(@NonNull View view) {

    }
}
