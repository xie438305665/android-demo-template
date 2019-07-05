package com.demo.template.mvp.presenter.impl;

import android.text.TextUtils;

import com.android.library.bridge.core.MVPresenterImpl;
import com.android.library.bridge.util.UIUtils;
import com.android.library.net.NetRequest;
import com.demo.template.R;
import com.demo.template.mvp.presenter.RetrievePwTemplatePresenter;
import com.demo.template.mvp.view.RetrievePwTemplateView;

public class RetrievePwTemplatePresenterImpl extends MVPresenterImpl<RetrievePwTemplateView> implements RetrievePwTemplatePresenter {

    public RetrievePwTemplatePresenterImpl(RetrievePwTemplateView view) {
        super(view);
    }

    @Override
    public void onVerification(String user) {
        if (TextUtils.isEmpty(user)) {
            UIUtils.show(UIUtils.getString(R.string.retrieve_edit_input_user_hint));
            return;
        }
        getView().onVerification(false);
    }

    @Override
    public void onChangePw(String pw, String repeatPw) {
        if (TextUtils.isEmpty(pw) || TextUtils.isEmpty(repeatPw)) {
            UIUtils.show(UIUtils.getString(R.string.change_pw_empty));
            return;
        }
        getView().onFinish();
    }

    @Override
    public void onDestroy() {
        NetRequest.single().cancel(getClass().getSimpleName());
        super.onDestroy();
    }
}
