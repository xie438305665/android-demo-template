package com.android.library.bridge.util;

import android.app.Activity;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.library.bridge.R;

public class MDialogUtils {

    public static void logout(Activity activity, MaterialDialog.SingleButtonCallback singleButtonCallback) {
        new MaterialDialog
                .Builder(activity)
                .title(R.string.logout)
                .titleGravity(GravityEnum.CENTER)
                .content(R.string.logout_dialog_content)
                .contentGravity(GravityEnum.CENTER)
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
