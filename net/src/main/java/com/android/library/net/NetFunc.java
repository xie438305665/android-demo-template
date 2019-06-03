package com.android.library.net;

import android.text.TextUtils;

import com.android.library.net.entity.BaseEntity;

import io.reactivex.functions.Function;

public class NetFunc<T> implements Function<T, T> {
    @Override
    public T apply(T t) {
        if (!(t instanceof BaseEntity)) {
            throw new NetException(NetUrl.DATA_NULL);
        }
        if (TextUtils.equals(NetUrl.ERROR_CODE, ((BaseEntity) t).getCode())) {
            throw new NetException(NetUrl.TOKEN_ERROR_QUIT);
        }
        if (!TextUtils.equals(NetUrl.SUCCESS_CODE, ((BaseEntity) t).getCode())) {
            throw new NetException(((BaseEntity) t).getMessage());
        }
        return t;
    }
}
