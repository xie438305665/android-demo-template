package com.demo.template.mvp.presenter;

import com.android.library.bridge.core.base.IPresenter;

public interface ChangePwTemplatePresenter extends IPresenter {
    void onVerification(String oldPw);

    void onChangePw(String oldPw, String pw, String repeatPw);
}
