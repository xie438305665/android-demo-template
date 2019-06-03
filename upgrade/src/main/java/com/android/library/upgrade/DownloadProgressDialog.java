package com.android.library.upgrade;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.Toast;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.exception.FileDownloadOutOfSpaceException;

/**
 * @author xcl
 */

public class DownloadProgressDialog {

    private static final String DIALOG_FORMAT_MB = "%sMB/%sMB";
    private Activity activity;
    private String downloadUrl;
    private String downloadPath;
    private String title;
    private String message;
    private Call downloadCallBack;
    private Object tag;
    private int icon;
    private ProgressDialog dialog;

    private DownloadProgressDialog(Activity activity) {
        this.activity = activity;
        dialog = new ProgressDialog(activity);
    }

    public static DownloadProgressDialog create(Activity activity) {
        FileDownloader.setup(activity);
        return new DownloadProgressDialog(activity);
    }

    public DownloadProgressDialog setTag(Object tag) {
        this.tag = tag;
        return this;
    }

    public DownloadProgressDialog setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
        return this;
    }

    public DownloadProgressDialog setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
        return this;
    }

    public DownloadProgressDialog setDownloadCallBack(Call downloadCallBack) {
        this.downloadCallBack = downloadCallBack;
        return this;
    }

    public DownloadProgressDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public DownloadProgressDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public DownloadProgressDialog setIcon(int icon) {
        this.icon = icon;
        return this;
    }

    public DownloadProgressDialog start() {
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setIcon(icon);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setProgressNumberFormat(DIALOG_FORMAT_MB);
        FileDownloader.getImpl()
                .create(downloadUrl)
                .setForceReDownload(true)
                .setPath(downloadPath)
                .setTag(tag)
                .setListener(new FileDownloadListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        show();
                        downloadCallBack.start(task);
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        if (totalBytes > 0) {
                            if (totalBytes / 1000000 > 0) {
                                dialog.setMax(totalBytes / 1000000);
                                dialog.setProgress(soFarBytes / 1000000);
                            } else {
                                dialog.setMax(1);
                                dialog.setProgress(1);
                            }
                        }
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        dismiss();
                        downloadCallBack.error(task);
                        if (task.getErrorCause() instanceof FileDownloadOutOfSpaceException) {
                            Toast.makeText(activity, activity.getString(R.string.upgrade_error_file_outofspace), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(activity, activity.getString(R.string.upgrade_error), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {

                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        dismiss();
                        downloadCallBack.completed(task);
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }
                }).start();
        return this;
    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing() && !activity.isFinishing()) {
            dialog.dismiss();
        }
    }

    public void show() {
        if (dialog != null && activity != null && !activity.isFinishing()) {
            dialog.show();
        }
    }

    public interface Call {
        void start(BaseDownloadTask task);

        void error(BaseDownloadTask task);

        void completed(BaseDownloadTask task);
    }

    public static class SimpleCall implements Call {
        @Override
        public void start(BaseDownloadTask task) {

        }

        @Override
        public void error(BaseDownloadTask task) {

        }

        @Override
        public void completed(BaseDownloadTask task) {

        }
    }


}
