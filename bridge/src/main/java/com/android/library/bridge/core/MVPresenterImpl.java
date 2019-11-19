package com.android.library.bridge.core;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.android.library.bridge.core.base.IView;
import com.android.library.net.NetRequest;

import io.reactivex.Observable;
import io.reactivex.network.RxNetWorkListener;

public abstract class MVPresenterImpl<V extends IView> extends MVPLifecyclePresenterImpl<V> {

    public MVPresenterImpl(V view) {
        super(view);
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        super.onDestroy(owner);
        if (NetRequest.single() == null) return;
        NetRequest.single().useless();
    }

    @Deprecated
    public <M> void NetLife(@NonNull Object tag, @NonNull Observable<M> observable, @NonNull RxNetWorkListener<M> listener) {
        NetRequest.single().getApi(tag, observable.compose(bindLifecycle()), listener);
    }
}
