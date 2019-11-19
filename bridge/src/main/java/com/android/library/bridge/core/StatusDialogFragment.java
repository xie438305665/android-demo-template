package com.android.library.bridge.core;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.android.library.bridge.core.base.BaseDialogFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author xcl
 * @create 2018/12/4
 */
public abstract class StatusDialogFragment extends BaseDialogFragment {

    protected View mRootView = null;
    protected AlertDialog mAlertDialog = null;
    protected Activity mActivity;
    protected Bundle bundle;
    private Unbinder bind;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            mActivity = (Activity) context;
        } else {
            mActivity = getActivity();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        if (bundle == null) {
            bundle = Bundle.EMPTY;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setCancelable(getCancelable());
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity, themeResId());
        mRootView = getRootView(getLayoutId());
        builder.setView(mRootView);
        mAlertDialog = builder.create();
        return mAlertDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = ButterKnife.bind(this, mRootView);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mRootView != null) {
            mRootView = null;
        }
        if (bind != null) {
            bind.unbind();
        }
    }
}