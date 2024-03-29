package com.demo.template.mvp.presenter.impl;

import android.text.TextUtils;

import com.demo.template.R;
import com.demo.template.mvp.presenter.LoginTemplatePresenter;
import com.demo.template.mvp.view.LoginTemplateView;
import com.android.library.bridge.core.MVPresenterImpl;
import com.android.library.bridge.util.UIUtils;

/**
 * @author xcl
 */
public class LoginTemplatePresenterImpl extends MVPresenterImpl<LoginTemplateView> implements LoginTemplatePresenter {

    public LoginTemplatePresenterImpl(LoginTemplateView view) {
        super(view);
    }

    @Override
    public void login(String user, String password) {

    }

    @Override
    public void check(String user, String password) {
        if (TextUtils.isEmpty(user)) {
            UIUtils.show(UIUtils.getString(R.string.login_user_empty));
            getView().checkInput(false);
            return;
        }
        if (TextUtils.isEmpty(password)) {
            UIUtils.show(UIUtils.getString(R.string.login_password_empty));
            getView().checkInput(false);
            return;
        }
        if (getView() == null) {
            return;
        }
        getView().checkInput(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
