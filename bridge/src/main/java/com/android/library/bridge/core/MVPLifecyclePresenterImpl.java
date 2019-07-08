package com.android.library.bridge.core;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.annotation.NonNull;

import com.android.library.bridge.core.base.IPresenter;
import com.android.library.bridge.core.base.IView;

import io.reactivex.ObservableTransformer;
import io.reactivex.subjects.BehaviorSubject;

/**
 * @param <V>
 * @author xcl
 */
public abstract class MVPLifecyclePresenterImpl<V extends IView> implements IPresenter {

    private V mView;
    private final BehaviorSubject<Lifecycle.Event> behaviorSubject = BehaviorSubject.create();

    protected <T> ObservableTransformer<T, T> bindLifecycle() {
        return observable -> observable.takeUntil(behaviorSubject.skipWhile(event -> event != Lifecycle.Event.ON_DESTROY));
    }

    public MVPLifecyclePresenterImpl(V view) {
        this.mView = view;
    }

    @Override
    public void onDestroy() {
        if (mView != null)
            mView = null;
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        behaviorSubject.onNext(Lifecycle.Event.ON_STOP);
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        behaviorSubject.onNext(Lifecycle.Event.ON_START);
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        behaviorSubject.onNext(Lifecycle.Event.ON_STOP);
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        behaviorSubject.onNext(Lifecycle.Event.ON_PAUSE);
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        behaviorSubject.onNext(Lifecycle.Event.ON_CREATE);
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        behaviorSubject.onNext(Lifecycle.Event.ON_DESTROY);
        if (mView != null)
            mView = null;
    }

    public V getView() {
        return mView;
    }
}
