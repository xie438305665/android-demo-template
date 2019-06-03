package com.android.library.bridge.core.listener;

import com.status.layout.Status;
import com.xadapter.widget.XRefreshView;
import com.android.library.bridge.annotation.UIType;
import com.android.library.bridge.core.base.BaseNetListener;
import com.android.library.bridge.core.base.IRefreshView;
import com.android.library.net.entity.BaseEntity;


/**
 * @author xcl
 */
public class SimpleRefreshNetListener<T> extends BaseNetListener<BaseEntity<T>> {
    private IRefreshView mView;
    private int type;

    public SimpleRefreshNetListener(IRefreshView mView, int type) {
        this.mView = mView;
        this.type = type;
    }

    @Override
    public void onNetWorkStart() {
        if (mView == null) {
            return;
        }
        if (type == UIType.STATUS) {
            mView.onChangeRootUI(Status.LOADING);
        }
    }

    @Override
    public void onNetWorkError(Throwable e) {
        super.onNetWorkError(e);
        if (mView == null) {
            return;
        }
        if (type == UIType.STATUS) {
            mView.onChangeRootUI(Status.ERROR);
            return;
        }
        mView.onRefreshState(XRefreshView.ERROR);
    }

    @Override
    public void onNetWorkComplete() {
    }

    @Override
    public void onNetWorkSuccess(BaseEntity<T> data) {
        if (mView == null) {
            return;
        }
        mView.onRemoveAll();
        if (type != UIType.STATUS) {
            mView.onRefreshState(XRefreshView.SUCCESS);
        }
        onNetSuccess(data.getData());
    }

    public void onNetSuccess(T data) {
    }
}
