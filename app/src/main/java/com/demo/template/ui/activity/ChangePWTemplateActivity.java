package com.demo.template.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.demo.template.R;
import com.demo.template.mvp.presenter.impl.ChangePwTemplatePresenterImpl;
import com.demo.template.mvp.view.ChangePwTemplateView;
import com.android.library.bridge.core.MVPActivity;
import com.android.library.bridge.util.UIUtils;
import com.android.library.widget.detector.KeyboardStatusDetector;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author xcl
 */
public class ChangePWTemplateActivity extends MVPActivity<ChangePwTemplatePresenterImpl, String> implements ChangePwTemplateView, KeyboardStatusDetector.KeyboardListener {

    @BindView(R.id.tv_pw_verification)
    AppCompatTextView mVerification;
    @BindView(R.id.tv_pw_new)
    AppCompatTextView mNewPw;
    @BindView(R.id.et_pw_verification)
    AppCompatEditText mEtVerification;
    @BindView(R.id.et_pw_repeat)
    AppCompatEditText mEtRepeat;
    @BindView(R.id.btn_pw_ok)
    AppCompatButton mOk;
    @BindView(R.id.pw_root_view)
    NestedScrollView mRootView;

    @BindString(R.string.change_pw_verification_hint)
    String pwVerificationHint;
    @BindString(R.string.change_pw_new_again_tips)
    String pwNewAgainTips;
    @BindString(R.string.change_pw_success)
    String changePwSuccess;

    private KeyboardStatusDetector keyboardStatusDetector;
    private String oldPw;

    @Override
    protected void initCreate(@Nullable Bundle savedInstanceState) {
        mCustomToolBar.setCenterTvText(R.string.change_password_title);
        mEtVerification.setHint(pwVerificationHint);
        mEtRepeat.setHint(null);
        mEtRepeat.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                mRootView.smoothScrollBy(0, 800);
            }
        });
        keyboardStatusDetector = new KeyboardStatusDetector(this);
        mRootView.getViewTreeObserver().addOnGlobalLayoutListener(keyboardStatusDetector);
    }

    @Override
    protected ChangePwTemplatePresenterImpl initPresenter() {
        return new ChangePwTemplatePresenterImpl(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_pw;
    }

    @Override
    protected boolean showToolBar() {
        return true;
    }

    @OnClick(R.id.btn_pw_ok)
    public void onViewClicked() {
        if (mEtRepeat.getVisibility() == View.GONE) {
            mPresenter.onVerification(mEtVerification.getText().toString().trim());
        } else {
            mPresenter.onChangePw(oldPw, mEtVerification.getText().toString().trim(), mEtRepeat.getText().toString().trim());
        }
    }

    @Override
    public void onVerification() {
        mVerification.setEnabled(true);
        mNewPw.setEnabled(false);
    }

    @Override
    public void onNew() {
        mVerification.setEnabled(false);
        mNewPw.setEnabled(true);
    }

    @Override
    public void onVerificationSuccess() {
        oldPw = mEtVerification.getText().toString().trim();
        mEtVerification.getText().clear();
        mEtVerification.setHint(pwVerificationHint);
        mEtRepeat.setHint(pwNewAgainTips);
        mEtRepeat.setVisibility(View.VISIBLE);
        UIUtils.openKeyboard(mEtVerification);
    }

    @Override
    public void onOkEnable(boolean flag) {
        mOk.setEnabled(flag);
        mEtRepeat.setEnabled(flag);
        mEtVerification.setEnabled(flag);
    }

    @Override
    public void onFinish() {
        UIUtils.show(changePwSuccess);
        finish();
    }

    @Override
    public View getKeyboardView() {
        return mRootView;
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
}
