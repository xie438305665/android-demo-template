package com.android.library.bridge.core.listener;

import android.support.annotation.Nullable;

import com.android.library.bridge.core.base.BaseNetListener;
import com.android.library.bridge.core.base.IView;
import com.android.library.net.entity.BaseEntity;

/**
 * @author xcl
 */
public abstract class SimpleNetSuccessListener<T> extends BaseNetListener<BaseEntity<T>> {

    private final IView mView;
    private boolean showProgress;

    public SimpleNetSuccessListener(IView mView, boolean showProgress) {
        this.mView = mView;
        this.showProgress = showProgress;
    }

    public SimpleNetSuccessListener(IView mView) {
        this.mView = mView;
        this.showProgress = true;
    }

    @Override
    public void onNetWorkSuccess(BaseEntity<T> data) {
        if (mView == null) {
            return;
        }
        onNetSuccess(data.getData());
    }

    @Override
    public void onNetWorkStart() {
        if (mView == null) {
            return;
        }
        if (showProgress) {
            mView.showProgress();
        }
    }

    @Override
    public void onNetWorkComplete() {
        if (mView == null) {
            return;
        }
        if (showProgress) {
            mView.hideProgress();
        }
    }

    @Override
    public void onNetWorkError(Throwable e) {
        super.onNetWorkError(e);
        if (mView == null) {
            return;
        }
        if (showProgress) {
            mView.hideProgress();
        }
        mView.onViewError(e);
    }

    protected abstract void onNetSuccess(@Nullable T data);
}
