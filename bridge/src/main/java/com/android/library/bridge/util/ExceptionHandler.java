package com.android.library.bridge.util;

/**
 * @author xcl
 * @create 2019/1/3
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    private Callback callback;

    public ExceptionHandler(Callback callback) {
        this.callback = callback;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (callback != null) {
            callback.uncaughtException();
        }
    }

    public interface Callback {
        void uncaughtException();
    }
}