package com.demo.template.ui.grade.common.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.android.library.bridge.core.StatusDialogFragment;
import com.android.library.widget.custom.CustomWebView;
import com.demo.template.R;
import com.demo.template.annotation.BundleKey;

import butterknife.BindView;
import butterknife.OnClick;

public class LookAnswerDialog extends StatusDialogFragment {

    @BindView(R.id.grade_look_answer)
    CustomWebView webView;

    public static void shows(@NonNull FragmentManager fragmentManager, String answer) {
        LookAnswerDialog dialog = new LookAnswerDialog();
        Bundle bundle = new Bundle();
        bundle.putString(BundleKey.LOOK_ANSWER, answer);
        dialog.setArguments(bundle);
        dialog.show(fragmentManager, LookAnswerDialog.class.getSimpleName());
    }

    @OnClick({R.id.grade_look_finish})
    public void onViewClicked(View view) {
        dismiss();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null) {
            return;
        }
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (attributes == null) {
            return;
        }
        attributes.width = LinearLayout.LayoutParams.MATCH_PARENT;
        attributes.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        attributes.gravity = Gravity.BOTTOM;
        window.setAttributes(attributes);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String answer = bundle.getString(BundleKey.LOOK_ANSWER, "");
        webView.loadDataUrl(answer);
        webView.setBackgroundColor(0);
    }

    @Override
    public int getLayoutId() {
        return R.layout.grade_dialog_look_answer;
    }

    @Override
    protected int themeResId() {
        return R.style.BottomDialog;
    }

    @Override
    protected boolean getCancelable() {
        return true;
    }

    @Override
    public void onDestroyView() {
        webView.reset();
        super.onDestroyView();
    }
}
