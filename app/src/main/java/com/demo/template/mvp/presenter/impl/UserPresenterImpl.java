package com.demo.template.mvp.presenter.impl;


import com.demo.template.mvp.presenter.UserPresenter;
import com.demo.template.mvp.view.UserView;
import com.android.library.bridge.core.MVPresenterImpl;


public class UserPresenterImpl extends MVPresenterImpl<UserView> implements UserPresenter {

    public UserPresenterImpl(UserView view) {
        super(view);
    }
}
