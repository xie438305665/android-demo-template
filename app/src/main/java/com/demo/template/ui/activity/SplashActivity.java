package com.demo.template.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;

import com.android.library.bridge.BridgeConstant;
import com.android.library.bridge.core.MVPActivity;
import com.android.library.bridge.core.MVPresenterImpl;
import com.android.library.bridge.util.SpUtils;
import com.android.library.bridge.util.UIUtils;
import com.demo.template.R;

import butterknife.BindView;

/**
 * @author xcl
 */
public class SplashActivity extends MVPActivity {

    @BindView(R.id.splash_root_view)
    AppCompatImageView mRootView;

    @Override
    protected void initCreate(@Nullable Bundle savedInstanceState) {
        UIUtils.hideStatusBar(this);
        Class cls = TextUtils.isEmpty(SpUtils.getString(BridgeConstant.TOKEN)) ? LoginActivity.class : MainActivity.class;
        new Handler().postDelayed(() -> {
            UIUtils.startActivity(cls);
            finish();
        }, 300);
    }

    @Override
    protected MVPresenterImpl initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected boolean showToolBar() {
        return false;
    }
}
