package com.android.library.bridge.core;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.socks.library.KLog;
import com.android.library.bridge.core.base.IPresenter;
import com.android.library.bridge.core.base.IView;
import com.android.library.bridge.util.UIUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @author xcl
 */

public abstract class MVPActivity<P extends IPresenter, ENTITY>
        extends StatusActivity implements IView<ENTITY> {

    protected P mPresenter;
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ButterKnife.bind(this);
        mPresenter = initPresenter();
        if (!UIUtils.checkNull(mPresenter)) {
            getLifecycle().addObserver(mPresenter);
        }
        initToolBar();
        initCreate(savedInstanceState);
    }

    protected abstract void initCreate(@Nullable Bundle savedInstanceState);

    protected abstract P initPresenter();


    protected void initToolBar() {
    }

    protected void onStatusRetry() {
    }

    @Override
    public void onEmptyClick(@NonNull View view) {
        super.onEmptyClick(view);
        onStatusRetry();
    }

    @Override
    public void onErrorClick(@NonNull View view) {
        super.onErrorClick(view);
        onStatusRetry();
    }

    @Override
    public void onViewSuccess(ENTITY entity) {
    }

    @Override
    public void onViewError(Throwable throwable) {
    }

    @Override
    public void onChangeRootUI(String status) {
        KLog.i(status);
        if (mStatusLayout != null) {
            mStatusLayout.setStatus(status);
        }
    }

    @Override
    public void showProgress() {
        showLoading();
    }

    @Override
    public void hideProgress() {
        hideLoading();
    }

    protected void showLoading() {
        if (mProgress != null)
            mProgress.show();
    }

    protected void hideLoading() {
        if (mProgress != null)
            mProgress.hide();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!UIUtils.checkNull(mPresenter)) {
            getLifecycle().removeObserver(mPresenter);
        }
        if (mPresenter != null)
            mPresenter.onDestroy();
        if (bind != null)
            bind.unbind();
    }
}
