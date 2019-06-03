package com.demo.template.mvp.view;

import com.android.library.bridge.core.base.IView;

/**
 * @author xcl
 */
public interface LoginView extends IView<Object> {
    void checkInput(boolean flag);
}
