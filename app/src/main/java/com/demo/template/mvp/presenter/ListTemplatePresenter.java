package com.demo.template.mvp.presenter;

import com.android.library.bridge.core.base.IPresenter;

public interface ListTemplatePresenter extends IPresenter {
    void onNetWorkRequest(int page, int type);
}
