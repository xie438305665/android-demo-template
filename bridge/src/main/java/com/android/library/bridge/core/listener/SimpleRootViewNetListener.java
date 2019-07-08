package com.android.library.bridge.core.listener;

import androidx.annotation.NonNull;

import com.socks.library.KLog;
import com.status.layout.Status;
import com.android.library.bridge.annotation.UIType;
import com.android.library.bridge.core.base.BaseNetListener;
import com.android.library.bridge.core.base.IView;
import com.android.library.bridge.util.UIUtils;
import com.android.library.net.entity.BaseEntity;

/**
 * @param <T>
 * @author xcl
 */

public class SimpleRootViewNetListener<T> extends BaseNetListener<BaseEntity<T>> {

    private IView<T> mView;
    private int flag;

    public SimpleRootViewNetListener(@NonNull IView<T> mView, @UIType int flag) {
        this.mView = mView;
        this.flag = flag;
    }

    @Override
    public void onNetWorkStart() {
        if (UIUtils.checkNull(mView)) {
            return;
        }
        if (flag == UIType.STATUS) {
            mView.onChangeRootUI(Status.LOADING);
        } else {
            mView.showProgress();
        }
    }

    @Override
    public void onNetWorkError(Throwable e) {
        super.onNetWorkError(e);
        if (UIUtils.checkNull(mView)) {
            return;
        }
        mView.onViewError(e);
        if (flag == UIType.STATUS) {
            mView.onChangeRootUI(Status.ERROR);
        } else {
            mView.hideProgress();
        }
        mView.onViewError(e);
    }

    @Override
    public void onNetWorkComplete() {
        if (!UIUtils.checkNull(mView)) {
            mView.hideProgress();
        }
    }

    @Override
    public void onNetWorkSuccess(BaseEntity<T> data) {
        KLog.d("mView:" + mView);
        if (UIUtils.checkNull(mView)) {
            return;
        }
        onNetSuccess(data.getData());
    }

    protected void onNetSuccess(T data) {
        mView.onChangeRootUI(Status.SUCCESS);
        mView.onViewSuccess(data);
    }
}
