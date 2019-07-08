package com.android.library.bridge.interceptor;

import androidx.annotation.NonNull;

import com.android.library.bridge.BridgeConstant;
import com.android.library.bridge.util.SpUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json;charset=utf-8")
                .addHeader("Authorization", SpUtils.getString(BridgeConstant.TOKEN))
                .build();
        return chain.proceed(request);
    }
}
