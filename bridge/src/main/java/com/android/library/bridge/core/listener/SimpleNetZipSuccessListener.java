package com.android.library.bridge.core.listener;

import com.status.layout.Status;
import com.android.library.bridge.core.base.BaseNetListener;
import com.android.library.bridge.core.base.IView;

/**
 * @author xcl
 */
public abstract class SimpleNetZipSuccessListener<T> extends BaseNetListener<T> {

    private final IView mView;
    private boolean showProgress;
    private boolean hasStatus;

    public SimpleNetZipSuccessListener(IView mView, boolean showProgress) {
        this.mView = mView;
        this.showProgress = showProgress;
    }

    public SimpleNetZipSuccessListener(IView mView) {
        this.mView = mView;
        this.showProgress = true;
    }

    public boolean isHasStatus() {
        return hasStatus;
    }

    public SimpleNetZipSuccessListener<T> setHasStatus(boolean hasStatus) {
        this.hasStatus = hasStatus;
        return this;
    }

    @Override
    public void onNetWorkStart() {
        if (mView == null) {
            return;
        }
        if (hasStatus) {
            mView.onChangeRootUI(Status.LOADING);
        } else if (showProgress) {
            mView.showProgress();
        }
    }

    @Override
    public void onNetWorkComplete() {
        if (mView == null) {
            return;
        }
        if (hasStatus) {
            mView.onChangeRootUI(Status.SUCCESS);
        } else if (showProgress) {
            mView.hideProgress();
        }
    }

    @Override
    public void onNetWorkError(Throwable e) {
        super.onNetWorkError(e);
        if (mView == null) {
            return;
        }
        if (hasStatus) {
            mView.onChangeRootUI(Status.ERROR);
        } else if (showProgress) {
            mView.hideProgress();
        }
        mView.onViewError(e);
    }
}
