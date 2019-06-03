package com.android.library.bridge.util;

import android.app.Activity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.library.bridge.R;

public class MDialogUtils {

    public static void logout(Activity activity, MaterialDialog.SingleButtonCallback singleButtonCallback) {
        new MaterialDialog
                .Builder(activity)
                .content(R.string.logout_dialog_content)
                .negativeText(android.R.string.cancel)
                .positiveText(android.R.string.ok)
                .onPositive(singleButtonCallback)
                .show();
    }

    public static void userHeader(Activity activity,
                                  MaterialDialog.SingleButtonCallback positiveCallback) {
        new MaterialDialog
                .Builder(activity)
                .title(R.string.upload_header)
                .negativeText(android.R.string.cancel)
                .positiveText(android.R.string.ok)
                .onPositive(positiveCallback)
                .show();
    }
}
