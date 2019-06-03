package com.demo.template.mvp.presenter.impl;

import com.demo.template.mvp.presenter.TODOPresenter;
import com.demo.template.mvp.view.TODOView;
import com.android.library.bridge.core.MVPresenterImpl;
import com.android.library.net.NetRequest;

public class TODOPresenterImpl extends MVPresenterImpl<TODOView>
        implements TODOPresenter {
    public TODOPresenterImpl(TODOView view) {
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
