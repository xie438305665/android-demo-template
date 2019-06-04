package com.demo.template.mvp.view;

import com.android.library.bridge.core.base.IView;

public interface RetrievePwView extends IView<String> {

    void onVerification(boolean isSuccess);

    void onOkEnable(boolean flag);

    void onFinish();
}
