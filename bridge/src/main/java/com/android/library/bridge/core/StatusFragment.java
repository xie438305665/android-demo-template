package com.android.library.bridge.core;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.socks.library.KLog;
import com.status.layout.OnStatusClickListener;
import com.status.layout.Status;
import com.status.layout.StatusLayout;
import com.android.library.bridge.R;
import com.android.library.bridge.core.base.BaseFragment;
import com.android.library.bridge.util.UIUtils;


/**
 * @author xcl
 */
public abstract class StatusFragment extends BaseFragment implements OnStatusClickListener {

    protected Activity mActivity;
    protected StatusLayout mStatusLayout;
    protected Bundle bundle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            mActivity = (Activity) context;
        } else {
            mActivity = getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        KLog.d(getClass().getSimpleName());
        if (mStatusLayout == null) {
            initView(inflater);
        }
        return mStatusLayout;
    }

    private void initView(@NonNull LayoutInflater inflater) {
        mStatusLayout = new StatusLayout(inflater.getContext());
        mStatusLayout.addSuccessView(getLayoutId());
        mStatusLayout.addEmptyView(R.layout.layout_empty);
        mStatusLayout.addLoadingView(R.layout.layout_loading);
        mStatusLayout.addErrorView(R.layout.layout_error);
        mStatusLayout.setStatus(Status.SUCCESS);
        mStatusLayout.setOnStatusClickListener(this);
    }

    protected abstract int getLayoutId();

    @Status
    public String getCurrentStatus() {
        if (UIUtils.checkNull(mStatusLayout)) {
            return Status.NORMAL;
        }
        return mStatusLayout.getStatus();
    }

    @Nullable
    public View getNorMalView() {
        return mStatusLayout.getView(Status.NORMAL);
    }

    @Nullable
    public View getEmptyView() {
        return mStatusLayout.getView(Status.EMPTY);
    }

    @Nullable
    public View getErrorView() {
        return mStatusLayout.getView(Status.ERROR);
    }

    @Nullable
    public View getLoadingView() {
        return mStatusLayout.getView(Status.LOADING);
    }

    @Nullable
    public View getSuccessView() {
        return mStatusLayout.getView(Status.SUCCESS);
    }

    @Override
    public void onNorMalClick(@NonNull View view) {

    }

    @Override
    public void onLoadingClick(@NonNull View view) {

    }

    @Override
    public void onEmptyClick(@NonNull View view) {

    }

    @Override
    public void onSuccessClick(@NonNull View view) {

    }

    @Override
    public void onErrorClick(@NonNull View view) {

    }
}
