package com.android.library.bridge.core;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.library.bridge.core.base.IPresenter;
import com.android.library.bridge.core.base.IView;
import com.android.library.bridge.util.UIUtils;
import com.socks.library.KLog;

import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author xcl
 */

public abstract class MVPFragment<P extends IPresenter, ENTITY> extends StatusFragment implements IView<ENTITY> {

    protected P mPresenter;
    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        bind = ButterKnife.bind(this, Objects.requireNonNull(view));
        mPresenter = initPresenter();
        if (!UIUtils.checkNull(mPresenter)) {
            getLifecycle().addObserver(mPresenter);
        }
        initCreate(inflater, container, savedInstanceState);
        return view;
    }


    protected abstract void initCreate(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    protected abstract P initPresenter();

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
    public void showProgress() {
        showLoading();
    }

    @Override
    public void hideProgress() {
        hideLoading();
    }

    @Override
    public void onViewSuccess(ENTITY entity) {
    }

    @Override
    public void onViewError(Throwable throwable) {
    }

    @Override
    public void onChangeRootUI(String status) {
        if (mStatusLayout != null) {
            mStatusLayout.setStatus(status);
        }
    }


    protected void showLoading() {
        KLog.d("showLoading");
        if (mActivity instanceof MVPActivity && UIUtils.checkNotNull(((MVPActivity) mActivity).mProgress)) {
            ((MVPActivity) mActivity).mProgress.show();
        }
    }

    protected void hideLoading() {
        KLog.d("hideLoading");
        if (mActivity instanceof MVPActivity && UIUtils.checkNotNull(((MVPActivity) mActivity).mProgress)) {
            ((MVPActivity) mActivity).mProgress.hide();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hideLoading();
        if (mPresenter != null)
            mPresenter.onDestroy();
        if (bind != null)
            bind.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!UIUtils.checkNull(mPresenter)) {
            getLifecycle().removeObserver(mPresenter);
        }
    }
}
