package com.android.library.bridge.core.base;

import android.text.TextUtils;

import com.socks.library.KLog;
import com.android.library.bridge.R;
import com.android.library.bridge.User;
import com.android.library.bridge.util.UIUtils;
import com.android.library.net.NetException;
import com.android.library.net.NetUrl;

import io.reactivex.network.RxNetWorkListener;

/**
 * * @author xcl
 *
 * @param <T>
 */
public abstract class BaseNetListener<T> implements RxNetWorkListener<T> {
    @Override
    public void onNetWorkError(Throwable e) {
        KLog.i(e.toString());
        if (e instanceof NetException) {
            UIUtils.show(e.getMessage());
        } else {
            UIUtils.show(R.string.net_error);
        }
        if (TextUtils.equals(e.getMessage(), NetUrl.TOKEN_ERROR_QUIT)) {
            User.quit();
        }
    }
}
