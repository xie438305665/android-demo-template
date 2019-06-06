package com.demo.template.mvp.presenter.impl;


import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;

import com.demo.template.mvp.presenter.MainPresenter;
import com.demo.template.mvp.view.MainView;
import com.android.library.bridge.core.MVPresenterImpl;


public class MainPresenterImpl extends MVPresenterImpl<MainView> implements MainPresenter {

    public MainPresenterImpl(MainView view) {
        super(view);
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
//        super.onDestroy(owner);
    }
}
