package com.demo.template.mvp.view;

import com.android.library.bridge.core.base.IView;

public interface ChangePwTemplateView extends IView<String> {
    void onVerification();

    void onNew();

    void onVerificationSuccess();

    void onOkEnable(boolean flag);

    void onFinish();
}
