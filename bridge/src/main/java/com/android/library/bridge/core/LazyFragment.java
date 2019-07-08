package com.android.library.bridge.core;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.library.bridge.core.base.IPresenter;


/**
 * @author xcl
 */
public abstract class LazyFragment<P extends IPresenter, ENTITY> extends MVPFragment<P, ENTITY> {
    private boolean isVisible = true; // getUserVisibleHint()
    private boolean isFirstLoad;
    private boolean isInitView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View convertView = super.onCreateView(inflater, container, savedInstanceState);
        isInitView = true;
        return convertView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lazyData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        if (isVisibleToUser) {
            lazyData();
        }
    }

    protected void lazyData() {
        if (isFirstLoad || !isVisible || !isInitView) {
            return;
        }
        initActivityCreated();
        isFirstLoad = true;
    }

    protected abstract void initActivityCreated();

}
