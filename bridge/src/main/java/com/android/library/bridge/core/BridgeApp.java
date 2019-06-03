package com.android.library.bridge.core;

import android.app.Application;

import com.album.Album;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.library.bridge.BuildConfig;
import com.android.library.bridge.User;
import com.android.library.bridge.interceptor.HeaderInterceptor;
import com.android.library.bridge.interceptor.LogInterceptor;
import com.android.library.bridge.util.AlbumUtils;
import com.android.library.bridge.util.ExceptionHandler;
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
public class BridgeApp extends Application implements ExceptionHandler.Callback {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "项目key", !BuildConfig.DEBUG);
        SpUtils.init(getApplicationContext(), "DEMO");
        KLog.init(BuildConfig.LOG);
        GreenDaoManager.register(getApplicationContext());
        UIUtils.register(getApplicationContext());
        RxNetWork.getInstance().setBaseUrl(NetUrl.getDefaultBaseUrl()).setHeaderInterceptor(
                new HeaderInterceptor()).setLogInterceptor(new LogInterceptor());
        Album.getInstance().setAlbumImageLoader(new AlbumUtils.SimpleGlide4xAlbumImageLoader());
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
        ToastUtils.init(this, new ToastStyle());
//        new ExceptionHandler(this);
    }

    @Override
    public void uncaughtException() {
        User.reset();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
