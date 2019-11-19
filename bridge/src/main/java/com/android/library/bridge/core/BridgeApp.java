package com.android.library.bridge.core;

import android.app.Application;

import com.android.library.bridge.BridgeConstant;
import com.android.library.bridge.BuildConfig;
import com.android.library.bridge.album.Album;
import com.android.library.bridge.interceptor.HeaderInterceptor;
import com.android.library.bridge.interceptor.LogInterceptor;
import com.android.library.bridge.util.AlbumUtils;
import com.android.library.bridge.util.SpUtils;
import com.android.library.bridge.util.ToastStyle;
import com.android.library.bridge.util.UIUtils;
import com.android.library.db.GreenDaoManager;
import com.android.library.net.NetUrl;
import com.hjq.toast.ToastUtils;
import com.socks.library.KLog;
import com.tencent.bugly.crashreport.CrashReport;

import io.reactivex.network.RxNetWork;


/**
 * @author xcl
 * @create 2018/12/18
 */
public class BridgeApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), BridgeConstant.BUG_LY_KEY, !BuildConfig.DEBUG);
        SpUtils.init(getApplicationContext(), BridgeConstant.SP_FILE_NAME);
        KLog.init(BuildConfig.LOG);
        GreenDaoManager.register(getApplicationContext());
        UIUtils.register(getApplicationContext());
        RxNetWork.getInstance()
                .setBaseUrl(NetUrl.getDefaultBaseUrl())
                .setHeaderInterceptor(new HeaderInterceptor())
                .setLogInterceptor(new LogInterceptor());
        Album.getInstance().setAlbumImageLoader(new AlbumUtils.SimpleGlide4xAlbumImageLoader());
        if (!UIUtils.isDebug(getApplicationContext())) {
//            ExceptionHandler mCustomCrashHandler = ExceptionHandler.getInstance();
//            mCustomCrashHandler.setCustomCrashHandler(this,MainActivity.class);
        }
        ToastUtils.init(this, new ToastStyle());
    }
}
