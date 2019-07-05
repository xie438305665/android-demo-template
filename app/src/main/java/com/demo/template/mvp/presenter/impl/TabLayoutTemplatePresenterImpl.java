package com.demo.template.mvp.presenter.impl;


import com.demo.template.mvp.presenter.TabLayoutTemplatePresenter;
import com.demo.template.mvp.view.TabLayoutTemplateView;
import com.android.library.bridge.core.MVPresenterImpl;


public class TabLayoutTemplatePresenterImpl extends MVPresenterImpl<TabLayoutTemplateView> implements TabLayoutTemplatePresenter {

    public TabLayoutTemplatePresenterImpl(TabLayoutTemplateView view) {
        super(view);
    }
}
