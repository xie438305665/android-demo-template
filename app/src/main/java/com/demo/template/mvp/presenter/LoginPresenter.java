package com.demo.template.mvp.presenter;

import com.android.library.bridge.core.base.IPresenter;

/**
 * @author xcl
 */
public interface LoginPresenter extends IPresenter {
    void login(String user, String password);

    void check(String user, String password);
}
