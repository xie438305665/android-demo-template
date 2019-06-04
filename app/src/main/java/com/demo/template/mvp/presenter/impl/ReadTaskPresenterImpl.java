package com.demo.template.mvp.presenter.impl;

import com.android.library.bridge.core.MVPresenterImpl;
import com.android.library.net.NetRequest;
import com.demo.template.mvp.presenter.ReadTaskPresenter;
import com.demo.template.mvp.view.ReadTaskView;

public class ReadTaskPresenterImpl extends MVPresenterImpl<ReadTaskView>
        implements ReadTaskPresenter {
    public ReadTaskPresenterImpl(ReadTaskView view) {
        super(view);
    }

    @Override
    public void onNetWorkRequest(int page, int type) {
//        NetRequest.single().requestList(getClass().getSimpleName(), NetRequest.single().getService()
//                        .alreadyCompleteMarkingTask(1, page),
//                new SimpleRootListNetListener<>(getView(), type));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        NetRequest.single().cancel(getClass().getSimpleName());
    }
}
