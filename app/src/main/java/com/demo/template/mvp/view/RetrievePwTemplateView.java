package com.demo.template.mvp.view;

import com.android.library.bridge.core.base.IView;

public interface RetrievePwTemplateView extends IView<String> {

    void onVerification(boolean isSuccess);

    void onOkEnable(boolean flag);

    void onFinish();
}
