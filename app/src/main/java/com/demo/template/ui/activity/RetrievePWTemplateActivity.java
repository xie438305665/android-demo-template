package com.demo.template.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;

import com.android.library.bridge.core.MVPActivity;
import com.android.library.bridge.util.UIUtils;
import com.android.library.bridge.util.ViewUtils;
import com.android.library.widget.detector.KeyboardStatusDetector;
import com.demo.template.R;
import com.demo.template.annotation.RetrievePWType;
import com.demo.template.mvp.presenter.impl.RetrievePwTemplatePresenterImpl;
import com.demo.template.mvp.view.RetrievePwTemplateView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author xcl
 */
public class RetrievePWTemplateActivity extends MVPActivity<RetrievePwTemplatePresenterImpl, String>
        implements RetrievePwTemplateView, KeyboardStatusDetector.KeyboardListener, View.OnFocusChangeListener {

    @BindView(R.id.tv_retrieve_user_verification)
    AppCompatTextView mVerification;
    @BindView(R.id.tv_retrieve_setting_pw_new)
    AppCompatTextView mSettingNewPw;
    @BindView(R.id.tv_retrieve_fill_user)
    AppCompatTextView mFillUser;
    @BindView(R.id.tv_verification_result)
    AppCompatTextView mVerificationResult;

    @BindView(R.id.et_retrieve_user_verification)
    AppCompatEditText mEtVerification;
    @BindView(R.id.et_retrieve_verification_code)
    AppCompatEditText mEtCode;

    @BindView(R.id.btn_retrieve_pw_ok)
    AppCompatButton mOk;
    @BindView(R.id.retrieve_pw_root_view)
    NestedScrollView mRootView;

    @BindString(R.string.change_pw_success)
    String changePwSuccessToast;
    @BindString(R.string.retrieve_user_verification_success_toast)
    String successToast;
    @BindString(R.string.retrieve_user_verification_error_toast)
    String errorToast;
    @BindString(R.string.change_pw_new_hint_tips)
    String pwNewHint;
    @BindString(R.string.change_pw_new_again_tips)
    String pwNewAgainTipsHint;
    @BindString(R.string.retrieve_edit_input_code_hint)
    String codeHint;
    @BindString(R.string.retrieve_edit_input_user_hint)
    String userHint;
    @BindString(R.string.retrieve_next_btn)
    String btnNextStr;
    @BindString(R.string.retrieve_pre_btn)
    String btnPreStr;
    @BindString(R.string.ok)
    String okStr;

    private KeyboardStatusDetector keyboardStatusDetector;
    private boolean isSuccess = false;
    private int type = RetrievePWType.FILL_USER;

    @Override
    protected void initCreate(@Nullable Bundle savedInstanceState) {
        mCustomToolBar.setCenterTvText(R.string.retrieve_password_title);
        mEtVerification.setOnFocusChangeListener(this);
        mEtCode.setOnFocusChangeListener(this);
        keyboardStatusDetector = new KeyboardStatusDetector(this);
        mRootView.getViewTreeObserver().addOnGlobalLayoutListener(keyboardStatusDetector);
        ViewUtils.gone(mVerificationResult);
    }

    @Override
    protected RetrievePwTemplatePresenterImpl initPresenter() {
        return new RetrievePwTemplatePresenterImpl(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_retrieve_pw;
    }

    @Override
    protected boolean showToolBar() {
        return true;
    }

    @OnClick(R.id.btn_retrieve_pw_ok)
    public void onViewClicked() {
        switch (type) {
            case RetrievePWType.FILL_USER:
                mPresenter.onVerification(mEtVerification.getText().toString().trim());
                break;
            case RetrievePWType.VERIFICATION:
                ViewUtils.goneView(mVerificationResult);
                ViewUtils.visibleView(mEtCode, mEtVerification);
                mEtVerification.setHint(isSuccess ? pwNewHint : userHint);
                mEtCode.setHint(isSuccess ? pwNewAgainTipsHint : codeHint);
                onOkEnable(true);
                mOk.setText(isSuccess ? okStr : btnNextStr);
                type = isSuccess ? RetrievePWType.SETTING_PW : RetrievePWType.FILL_USER;
                break;
            case RetrievePWType.SETTING_PW:
                mPresenter.onChangePw(mEtVerification.getText().toString().trim(), mEtCode.getText().toString().trim());
                break;
            default:
                break;
        }
    }

    @Override
    public void onVerification(boolean isSuccess) {
        ViewUtils.visibleView(mVerificationResult);
        ViewUtils.goneView(mEtCode, mEtVerification);
        this.isSuccess = isSuccess;
        type = RetrievePWType.VERIFICATION;
        mOk.setText(isSuccess ? btnNextStr : btnPreStr);
        mVerificationResult.setText(isSuccess ? successToast : errorToast);
        if (isSuccess) {
            mEtVerification.getText().clear();
            mEtCode.getText().clear();
        }
    }

    @Override
    public void onOkEnable(boolean flag) {
        mOk.setEnabled(flag);
        mEtCode.setEnabled(flag);
        mEtVerification.setEnabled(flag);
    }

    @Override
    public void onFinish() {
        UIUtils.show(changePwSuccessToast);
        finish();
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
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            mRootView.smoothScrollBy(0, 800);
        }
    }
}
