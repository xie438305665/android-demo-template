package com.android.library.bridge.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.library.net.NetRequest;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xcl
 * @create 2019/1/3
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    private static final String ERROR_MESSAGE = "出现了未知错误,即将退出";
    @SuppressLint("StaticFieldLeak")
    private static ExceptionHandler instance = new ExceptionHandler();
    @NonNull
    private String intentUri;
    private Context context;
    private Thread.UncaughtExceptionHandler defaultHandler;
    private Map<String, String> devInfo = new HashMap<>();
    private DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());

    public static ExceptionHandler getInstance() {
        return instance;
    }

    private static String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date());
    }

    public void setCustomCrashHandler(Context ctx, String intentUri) {
        this.context = ctx;
        this.intentUri = intentUri;
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        boolean isDone = doException(ex);
        if (!isDone && defaultHandler != null) {
            defaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ignored) {

            }
        }
        ActivityUtils.removeAllActivity();
        NetRequest.single().cancelAll();
        UIUtils.startActivity(intentUri);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    private boolean doException(Throwable ex) {
        if (ex == null) {
            return true;
        }
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        singleThreadPool.execute(() -> {
            Looper.prepare();
            Toast.makeText(context, ERROR_MESSAGE, Toast.LENGTH_LONG).show();
            Looper.loop();
        });
        singleThreadPool.shutdown();
        collectDeviceInfo(context);
        saveExceptionToFile(ex);
        return true;
    }

    private void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                devInfo.put("versionName", pi.versionName);
                devInfo.put("versionCode", String.valueOf(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ? pi.getLongVersionCode() : pi.versionCode));
                devInfo.put("MODEL", String.valueOf(Build.MODEL));
                devInfo.put("SDK_INT", String.valueOf(Build.VERSION.SDK_INT));
                devInfo.put("PRODUCT", String.valueOf(Build.PRODUCT));
                devInfo.put("TIME", String.valueOf(getCurrentTime()));
            }
        } catch (Exception ignored) {
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                devInfo.put(field.getName(), field.get(null).toString());
            } catch (Exception ignored) {
            }
        }
    }

    private void saveExceptionToFile(Throwable ex) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : devInfo.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key).append("=").append(value).append("\n");
        }
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        try {
            String time = df.format(new Date());
            String fileName = time + ".error_log";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                FileOutputStream fos = new FileOutputStream(FileUtils.getDiskFileDir(context, "error").getPath() + "/" + fileName);
                fos.write(sb.toString().getBytes());
                fos.close();
            }
        } catch (Exception ignored) {
        }
    }
}