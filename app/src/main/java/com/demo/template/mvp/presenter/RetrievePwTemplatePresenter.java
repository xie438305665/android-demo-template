package com.demo.template.mvp.presenter;

import com.android.library.bridge.core.base.IPresenter;

public interface RetrievePwTemplatePresenter extends IPresenter {
    void onVerification(String user);

    void onChangePw(String pw, String repeatPw);
}
