package com.demo.template.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.library.bridge.adapter.SimpleAdapter;
import com.android.library.bridge.annotation.UIType;
import com.android.library.bridge.core.MVPActivity;
import com.android.library.bridge.util.UIUtils;
import com.android.library.net.entity.template.AnnotationEntity;
import com.demo.template.R;
import com.demo.template.mvp.presenter.impl.NotePresenterImpl;
import com.demo.template.mvp.view.NoteView;
import com.demo.template.utils.MDialogUtils;
import com.status.layout.Status;
import com.xadapter.holder.XViewHolder;
import com.xadapter.listener.OnXBindListener;

import java.util.Iterator;
import java.util.List;

import butterknife.BindView;

/**
 * @author xcl
 * 添加批注
 */
public class NoteActivity extends MVPActivity<NotePresenterImpl, List<AnnotationEntity>>
        implements NoteView, OnXBindListener<AnnotationEntity> {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private SimpleAdapter<AnnotationEntity> mAdapter;

    @Override
    protected void initCreate(@Nullable Bundle savedInstanceState) {
        mCustomToolBar.setCenterTvText(R.string.grade_score_annotation);
        mCustomToolBar.getRightTv().setVisibility(View.VISIBLE);
        mCustomToolBar.getRightTv().setText(R.string.grade_add);
        mCustomToolBar.getRightTv().setOnClickListener(v -> MDialogUtils.annotationAdd(this, (dialog, input) ->
                mPresenter.onRequestPost(input.toString().trim())));
        mAdapter = new SimpleAdapter<>();
        mAdapter.setLayoutId(R.layout.grade_item_annotation).onXBind(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.onRequestList(UIType.STATUS);
    }

    @Override
    protected NotePresenterImpl initPresenter() {
        return new NotePresenterImpl(this);
    }

    @Override
    protected void onStatusRetry() {
        mPresenter.onRequestList(UIType.STATUS);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.grade_activity_annotation;
    }

    @Override
    protected boolean showToolBar() {
        return true;
    }

    @Override
    public void onViewSuccess(List<AnnotationEntity> annotationEntities) {
        mAdapter.removeAll();
        mAdapter.addAllData(annotationEntities);
    }

    @Override
    public void deleteSuccess(int labelId) {
        UIUtils.show(R.string.grade_delete_success);
        Iterator<AnnotationEntity> iterator = mAdapter.getData().iterator();
        while (iterator.hasNext()) {
            AnnotationEntity next = iterator.next();
            if (next.getLabelId() == labelId) {
                iterator.remove();
            }
        }
        if (mAdapter.getData().isEmpty()) {
            onChangeRootUI(Status.EMPTY);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void addSuccess() {
        UIUtils.show(R.string.grade_add_success);
        mPresenter.onRequestList(UIType.NOT_STATUS);
    }

    @Override
    public void onXBind(XViewHolder holder, int position, AnnotationEntity annotationEntity) {
        holder.setTextView(R.id.tv_annotation, annotationEntity.getMarkingLabel());
        holder.getView(R.id.iv_annotation).setOnClickListener(v -> MDialogUtils.annotationDelete(this, (dialog, which) -> mPresenter.onRequestDelete(annotationEntity.getLabelId())));
    }

}
