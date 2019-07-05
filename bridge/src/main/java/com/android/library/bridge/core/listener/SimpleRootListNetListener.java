package com.android.library.bridge.core.listener;

import com.status.layout.Status;
import com.android.library.bridge.adapter.ListAdapter;
import com.android.library.bridge.core.base.BaseNetListener;
import com.android.library.bridge.core.base.IRootListView;
import com.android.library.bridge.util.UIUtils;

import java.util.List;

/**
 * @author xcl
 */
public class SimpleRootListNetListener<T> extends BaseNetListener<List<T>> {

    private final IRootListView<T> rootView;
    private final int page;
    private final int type;

    public SimpleRootListNetListener(IRootListView<T> rootView, int type) {
        this.rootView = rootView;
        this.page = UIUtils.checkNull(rootView) ? 1 : rootView.getPage();
        this.type = type;
    }

    @Override
    public void onNetWorkStart() {
        if (rootView == null) {
            return;
        }
        if (type == ListAdapter.TYPE_STATUS) {
            rootView.onChangeRootUI(Status.LOADING);
        }
    }

    @Override
    public void onNetWorkError(Throwable e) {
        super.onNetWorkError(e);
        if (rootView == null) {
            return;
        }
        if (page == 1 && type == ListAdapter.TYPE_STATUS) {
            rootView.onChangeRootUI(Status.ERROR);
        } else {
            rootView.onNetError(type);
        }
    }

    @Override
    public void onNetWorkComplete() {
    }

    @Override
    public void onNetWorkSuccess(List<T> list) {
        if (list == null) {
            onNetWorkError(new NullPointerException());
            return;
        }
        if (rootView == null) {
            return;
        }
        if (page == 1) {
            rootView.onRemoveAll();
        }
        if (list.isEmpty()) {
            if (page == 1) {
                rootView.onChangeRootUI(Status.EMPTY);
            } else {
                rootView.onLoadNoMore();
            }
        } else {
            rootView.onViewSuccess(list);
            rootView.onPagePlus();
            if (page == 1 && type == ListAdapter.TYPE_STATUS) {
                rootView.onChangeRootUI(Status.SUCCESS);
            } else {
                rootView.onNetComplete(type);
            }
        }
    }
}
