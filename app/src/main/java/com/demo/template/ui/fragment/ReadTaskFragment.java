package com.demo.template.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.library.bridge.adapter.SimpleAdapter;
import com.android.library.bridge.core.MVPFragment;
import com.demo.template.R;
import com.demo.template.mvp.presenter.impl.ReadTaskPresenterImpl;
import com.demo.template.mvp.presenter.impl.TODOPresenterImpl;
import com.demo.template.mvp.view.ReadTaskView;
import com.demo.template.mvp.view.TODOView;
import com.socks.library.KLog;
import com.xadapter.holder.XViewHolder;
import com.xadapter.listener.LoadListener;
import com.xadapter.listener.OnXBindListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author xcl
 * @create 2019/5/30
 * 阅卷任务
 */
public class ReadTaskFragment extends MVPFragment<ReadTaskPresenterImpl, List<Object>>
        implements ReadTaskView, LoadListener, OnXBindListener<Object> {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private SimpleAdapter<Object> mAdapter;
    private int mPage = 1;

    public static ReadTaskFragment newInstance() {
        return new ReadTaskFragment();
    }

    @Override
    protected ReadTaskPresenterImpl initPresenter() {
        return new ReadTaskPresenterImpl(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_read_task;
    }

    @Override
    protected void initCreate(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new SimpleAdapter<>();
        mAdapter
                .onLoadMoreRetry(this::onLoadMore)
                .initXData(getData())
                .addRecyclerView(mRecyclerView)
                .setLayoutId(R.layout.item_todo)
                .setPullRefreshEnabled(true)
                .setLoadingMoreEnabled(true)
                .setLoadListener(this)
                .onXBind(this);
        mRecyclerView.setAdapter(mAdapter);
        onStatusRetry();
    }

    @Override
    protected void onStatusRetry() {
        super.onStatusRetry();
        mPresenter.onNetWorkRequest(mPage = 1, SimpleAdapter.TYPE_STATUS);
    }

    @Override
    public void onViewSuccess(List<Object> objects) {
        mAdapter.addAllData(objects);
    }

    @Override
    public void onXBind(XViewHolder holder, int position, Object o) {
        Button btnStartRead = holder.getButton(R.id.btn_todo_start_read);
        btnStartRead.setOnClickListener(v -> {
            KLog.d();
        });
    }

    @Override
    public void onRemoveAll() {
        mAdapter.removeAll();
    }

    @Override
    public void onNetComplete(int type) {
        mAdapter.onComplete(type);
    }

    @Override
    public void onNetError(int type) {
        mAdapter.onError(type);
    }

    @Override
    public void onLoadNoMore() {
        mAdapter.loadNoMore();
    }

    @Override
    public void onPagePlus() {
        mPage++;
    }

    @Override
    public int getPage() {
        return mPage;
    }

    @Override
    public void onRefresh() {
        mPresenter.onNetWorkRequest(mPage = 1, SimpleAdapter.TYPE_REFRESH);
    }

    @Override
    public void onLoadMore() {
        mPresenter.onNetWorkRequest(mPage, SimpleAdapter.TYPE_LOAD_MORE);
    }

    @Deprecated
    private List<Object> getData() {
        List<Object> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add(i);
        }
        return data;
    }
}
