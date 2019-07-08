package com.demo.template.mvp.presenter.impl;


import androidx.lifecycle.LifecycleOwner;
import androidx.annotation.NonNull;

import com.demo.template.mvp.presenter.MainTemplatePresenter;
import com.demo.template.mvp.view.MainTemplateView;
import com.android.library.bridge.core.MVPresenterImpl;


public class MainTemplatePresenterImpl extends MVPresenterImpl<MainTemplateView> implements MainTemplatePresenter {

    public MainTemplatePresenterImpl(MainTemplateView view) {
        super(view);
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
//        super.onDestroy(owner);
    }
}
