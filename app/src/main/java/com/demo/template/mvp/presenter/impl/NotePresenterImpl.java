package com.demo.template.mvp.presenter.impl;

import android.support.annotation.NonNull;

import com.android.library.bridge.annotation.UIType;
import com.android.library.bridge.core.MVPresenterImpl;
import com.demo.template.mvp.presenter.NotePresenter;
import com.demo.template.mvp.view.NoteView;

public class NotePresenterImpl extends MVPresenterImpl<NoteView> implements NotePresenter {


    public NotePresenterImpl(NoteView view) {
        super(view);
    }

    @Override
    public void onRequestList(@UIType int flag) {
//        NetRequest.single().request(GradeUrl.MARKING_LABELS, NetRequest.single().getService().markLabels(), new SimpleRootViewNetListener<>(getView(), flag));
    }

    @Override
    public void onRequestPost(@NonNull String markLabel) {
//        NetRequest.single().request(GradeUrl.MARKING_LABEL_ADD, NetRequest.single().getService().addMarkLabel(new AnnotationBody(markLabel)),
//                new SimpleNetSuccessListener<String>(getView()) {
//                    @Override
//                    protected void onNetSuccess(@Nullable String data) {
//                        getView().addSuccess();
//                    }
//                });
    }

    @Override
    public void onRequestDelete(int labelId) {
//        NetRequest.single().request(GradeUrl.MARKING_LABEL, NetRequest.single().getService().markLabel(labelId),
//                new SimpleNetSuccessListener<String>(getView()) {
//                    @Override
//                    protected void onNetSuccess(@Nullable String data) {
//                        getView().deleteSuccess(labelId);
//                    }
//                });
    }

    @Override
    public void onDestroy() {
//        NetRequest.single().cancel(GradeUrl.MARKING_LABEL_ADD, GradeUrl.MARKING_LABEL, GradeUrl.MARKING_LABELS);
        super.onDestroy();
    }
}
