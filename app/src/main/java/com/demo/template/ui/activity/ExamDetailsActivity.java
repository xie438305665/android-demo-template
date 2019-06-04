package com.demo.template.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.library.bridge.core.MVPActivity;
import com.android.library.bridge.util.UIUtils;
import com.demo.template.R;
import com.demo.template.mvp.presenter.impl.ExamDetailsPresenterImpl;
import com.demo.template.mvp.view.ExamDetailsView;

/**
 * @author xcl
 * @create 2019/6/4
 * 试卷详情
 */
public class ExamDetailsActivity extends MVPActivity<ExamDetailsPresenterImpl, Object> implements ExamDetailsView {

    public static void start() {
        UIUtils.startActivity(ExamDetailsActivity.class);
    }

    @Override
    protected void initCreate(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected ExamDetailsPresenterImpl initPresenter() {
        return new ExamDetailsPresenterImpl(this);
    }

    @Override
    protected boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        mCustomToolBar.setCenterTvText(R.string.exam_details_title);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_exam_details;
    }
}
