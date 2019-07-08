package com.android.library.bridge.interceptor;

import androidx.annotation.NonNull;

import com.socks.library.KLog;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LogInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(chain.request());
        ResponseBody body = response.body();
        if (body == null) {
            KLog.i("body == null");
            return response;
        }
        okhttp3.MediaType mediaType = body.contentType();
        String content = body.string();
        Response logResponse = response.networkResponse();
        if (logResponse != null) {
            KLog.i(logResponse.request().headers());
        }
        KLog.i(" url : " + request.url() + "   " + content);
        return response.newBuilder().body(ResponseBody.create(mediaType, content)).build();
    }
}