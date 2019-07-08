package com.android.library.net;

import androidx.collection.ArrayMap;

import com.android.library.net.entity.BaseEntity;
import com.android.library.net.entity.ListEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.network.RxNetWork;
import io.reactivex.network.RxNetWorkListener;

/**
 * @author xcl
 * @create 2019/1/29
 * <p>
 * 目前单例只为唯一的{@link Service}
 * 但是{{@link #request(Object, Observable, RxNetWorkListener)}}等方法不加{static}
 * 如果以后替换其他网络请求框架只需重写这几个即可,调用即单例
 */
public class NetRequest {

    private Service service;

    private NetRequest() {
    }

    private static final class NetRequestHolder {
        private static final NetRequest REQUEST = new NetRequest();
    }

    public static NetRequest single() {
        return NetRequestHolder.REQUEST;
    }

    public Service getService() {
        if (service == null) {
            service = RxNetWork.observable(Service.class);
        }
        return service;
    }

    public <M> void getApi(@NonNull Object tag, @NonNull Observable<M> observable, final RxNetWorkListener<M> listener) {
        NetRequest.single().cancel(tag);
        RxNetWork.getInstance().getApi(tag, observable, listener);
    }

    public <E> void request(Object tag, @NonNull Observable<BaseEntity<E>> observable, RxNetWorkListener<BaseEntity<E>> rxNetWorkListener) {
        getApi(tag, observable.map(new NetFunc<>()), rxNetWorkListener);
    }

    public <E> void requestList(Object tag, @NonNull Observable<ListEntity<E>> observable, RxNetWorkListener<List<E>> rxNetWorkListener) {
        getApi(tag, observable.map(new NetFunc<>()).map(entity -> entity.getData().getList()), rxNetWorkListener);
    }

    public void cancel(Object... objects) {
        for (Object o : objects) {
            if (o != null) {
                RxNetWork.getInstance().cancel(o);
            }
        }
    }

    public void useless() {
        ArrayMap<Object, Disposable> map = (ArrayMap<Object, Disposable>) RxNetWork.getInstance().getMap();
        for (Map.Entry<Object, Disposable> disposableEntry : map.entrySet()) {
            Disposable disposable = disposableEntry.getValue();
            if (disposable == null || disposable.isDisposed()) {
                map.remove(disposableEntry.getKey());
                return;
            }
        }
    }

    public void cancelAll() {
        RxNetWork.getInstance().cancelAll();
    }
}
