package com.demo.template.mvp.presenter.impl;


import com.android.library.bridge.core.MVPresenterImpl;
import com.demo.template.mvp.presenter.ExamDetailsPresenter;
import com.demo.template.mvp.view.ExamDetailsView;


public class ExamDetailsPresenterImpl extends MVPresenterImpl<ExamDetailsView> implements ExamDetailsPresenter {

    public ExamDetailsPresenterImpl(ExamDetailsView view) {
        super(view);
    }
}
