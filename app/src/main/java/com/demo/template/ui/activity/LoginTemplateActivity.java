package com.demo.template.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.widget.NestedScrollView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.library.bridge.RoutePath;
import com.android.library.bridge.core.MVPActivity;
import com.android.library.bridge.util.UIUtils;
import com.android.library.widget.detector.KeyboardStatusDetector;
import com.demo.template.R;
import com.demo.template.mvp.presenter.impl.LoginTemplatePresenterImpl;
import com.demo.template.mvp.view.LoginTemplateView;
import com.socks.library.KLog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author xcl
 */
@Route(path = RoutePath.LOGIN)
public class LoginTemplateActivity extends MVPActivity<LoginTemplatePresenterImpl, Object> implements LoginTemplateView, KeyboardStatusDetector.KeyboardListener {

    @BindView(R.id.login_et_user)
    AppCompatEditText mUser;
    @BindView(R.id.login_et_pw)
    AppCompatEditText mPw;
    @BindView(R.id.login_btn_sm)
    AppCompatButton mSubMit;
    @BindView(R.id.login_root_view)
    NestedScrollView mRootView;
    private KeyboardStatusDetector keyboardStatusDetector;

    @Override
    protected void initCreate(@Nullable Bundle savedInstanceState) {
        KLog.d(mPw);
        mPw.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                mRootView.smoothScrollBy(0, 800);
            }
        });
        keyboardStatusDetector = new KeyboardStatusDetector(this);
        mRootView.getViewTreeObserver().addOnGlobalLayoutListener(keyboardStatusDetector);
        if (UIUtils.isDebug()) {
            mUser.setText("");
            mPw.setText("");
        }
        mUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 11) {
                    s.delete(11, s.length());
                }
            }
        });
    }

    @Override
    protected LoginTemplatePresenterImpl initPresenter() {
        return new LoginTemplatePresenterImpl(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected boolean showToolBar() {
        return false;
    }

    @Override
    public void onViewSuccess(Object loginEntity) {
        UIUtils.startActivity(MainTemplateActivity.class);
        mUser.setEnabled(true);
        mPw.setEnabled(true);
        mSubMit.setEnabled(true);
        finish();
    }

    @Override
    public void onViewError(Throwable throwable) {
        mUser.setEnabled(true);
        mPw.setEnabled(true);
        mSubMit.setEnabled(true);
    }

    @OnClick({R.id.login_btn_sm, R.id.login_tv_retrieve_pw})
    public void onClick(View view) {
        if (R.id.login_btn_sm == view.getId()) {
            UIUtils.startActivity(MainTemplateActivity.class);
            finish();
//        mPresenter.check(mUser.getText().toString().trim(), mPw.getText().toString().trim());
            return;
        }
        UIUtils.startActivity(RetrievePWTemplateActivity.class);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onVisibilityChanged(boolean flag) {
        mRootView.smoothScrollBy(0, 800);
    }

    @Override
    protected void onDestroy() {
        mRootView.getViewTreeObserver().removeOnGlobalLayoutListener(keyboardStatusDetector);
        super.onDestroy();
    }

    @Override
    public void checkInput(boolean flag) {
        if (flag) {
            mUser.setEnabled(false);
            mPw.setEnabled(false);
            mSubMit.setEnabled(false);
            UIUtils.offKeyboard(mPw);
            mPresenter.login(mUser.getText().toString().trim(), mPw.getText().toString().trim());
        }
    }
}
