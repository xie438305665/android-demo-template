package com.demo.template.mvp.presenter.impl;

import android.text.TextUtils;

import com.demo.template.R;
import com.demo.template.mvp.presenter.ChangePwTemplatePresenter;
import com.demo.template.mvp.view.ChangePwTemplateView;
import com.android.library.bridge.core.MVPresenterImpl;
import com.android.library.bridge.util.UIUtils;
import com.android.library.net.NetRequest;

public class ChangePwTemplatePresenterImpl extends MVPresenterImpl<ChangePwTemplateView> implements ChangePwTemplatePresenter {

    public ChangePwTemplatePresenterImpl(ChangePwTemplateView view) {
        super(view);
    }

    @Override
    public void onVerification(String oldPw) {
        if (TextUtils.isEmpty(oldPw)) {
            UIUtils.show(UIUtils.getString(R.string.change_old_pw_empty));
            return;
        }
//        NetRequest.single().request(getClass().getSimpleName(), NetRequest.single().getService().verifyPw(new VerifyPwBody(oldPw)),
//                new SimpleNetSuccessListener<String>(getView()) {
//
//                    @Override
//                    public void onNetWorkStart() {
//                        super.onNetWorkStart();
//                        getView().onOkEnable(false);
//                        getView().onVerification();
//                    }
//
//                    @Override
//                    protected void onNetSuccess(@Nullable String data) {
//                        getView().onSettingNewPw();
//                        getView().onVerificationSuccess();
//                        getView().onOkEnable(true);
//                    }
//
//                    @Override
//                    public void onNetWorkError(Throwable e) {
//                        super.onNetWorkError(e);
//                        getView().onOkEnable(true);
//                    }
//                });
    }

    @Override
    public void onChangePw(String oldPw, String pw, String repeatPw) {
        if (TextUtils.isEmpty(pw)) {
            UIUtils.show(UIUtils.getString(R.string.change_pw_empty));
            return;
        }
        if (TextUtils.isEmpty(repeatPw)) {
            UIUtils.show(UIUtils.getString(R.string.change_pw_empty));
            return;
        }
//
//        NetRequest.single().request(getClass().getSimpleName(), NetRequest.single().getService().changePw(new ChangePwBody(oldPw, pw, repeatPw)),
//                new SimpleNetSuccessListener<String>(getView()) {
//                    @Override
//                    public void onNetWorkStart() {
//                        super.onNetWorkStart();
//                        getView().onOkEnable(false);
//                    }
//
//                    @Override
//                    protected void onNetSuccess(@Nullable String data) {
//                        getView().onOkEnable(true);
//                        getView().onFinish();
//                    }
//
//                    @Override
//                    public void onNetWorkError(Throwable e) {
//                        super.onNetWorkError(e);
//                        getView().onOkEnable(true);
//                    }
//                });
    }

    @Override
    public void onDestroy() {
        NetRequest.single().cancel(getClass().getSimpleName());
        super.onDestroy();
    }
}
