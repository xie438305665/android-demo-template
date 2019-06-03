package com.demo.template.mvp.presenter.impl;


import com.demo.template.mvp.presenter.ReadPresenter;
import com.demo.template.mvp.view.ReadView;
import com.android.library.bridge.core.MVPresenterImpl;


public class ReadPresenterImpl extends MVPresenterImpl<ReadView> implements ReadPresenter {

    public ReadPresenterImpl(ReadView view) {
        super(view);
    }
}
