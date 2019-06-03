package com.demo.template;

import com.android.library.bridge.core.BridgeApp;

/**
 * @author xcl
 */
public class App extends BridgeApp {

    @Override
    public void onCreate() {
        super.onCreate();
//        RxNetWork.getInstance().setBaseUrl(NetUrl.getBaseUrl(false));
    }
}
