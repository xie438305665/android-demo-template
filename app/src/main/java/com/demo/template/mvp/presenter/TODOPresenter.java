package com.demo.template.mvp.presenter;

import com.android.library.bridge.core.base.IPresenter;

public interface TODOPresenter extends IPresenter {
    void onNetWorkRequest(int page, int type);
}
