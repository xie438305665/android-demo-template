package com.demo.template.mvp.presenter.impl;


import android.support.annotation.NonNull;

import com.android.library.bridge.core.MVPresenterImpl;
import com.android.library.net.NetRequest;
import com.demo.template.mvp.presenter.WebViewTemplatePresenter;
import com.demo.template.mvp.view.WebViewTemplateView;

public class WebViewTemplatePresenterImpl extends MVPresenterImpl<WebViewTemplateView> implements WebViewTemplatePresenter {

    public WebViewTemplatePresenterImpl(WebViewTemplateView view) {
        super(view);
    }

    @Override
    public void onNetRequest(@NonNull String topicId) {

    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        NetRequest.single().cancel(getClass().getSimpleName());
    }
}
