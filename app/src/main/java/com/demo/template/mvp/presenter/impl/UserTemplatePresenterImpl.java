package com.demo.template.mvp.presenter.impl;


import com.demo.template.mvp.presenter.UserTemplatePresenter;
import com.demo.template.mvp.view.UserTemplateView;
import com.android.library.bridge.core.MVPresenterImpl;


public class UserTemplatePresenterImpl extends MVPresenterImpl<UserTemplateView> implements UserTemplatePresenter {

    public UserTemplatePresenterImpl(UserTemplateView view) {
        super(view);
    }
}
