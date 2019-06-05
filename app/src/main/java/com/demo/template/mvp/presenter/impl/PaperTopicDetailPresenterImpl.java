package com.demo.template.mvp.presenter.impl;


import android.support.annotation.NonNull;

import com.android.library.bridge.core.MVPresenterImpl;
import com.demo.template.mvp.presenter.PaperTopicDetailPresenter;
import com.demo.template.mvp.view.PaperTopicDetailView;

public class PaperTopicDetailPresenterImpl extends MVPresenterImpl<PaperTopicDetailView> implements PaperTopicDetailPresenter {

    public PaperTopicDetailPresenterImpl(PaperTopicDetailView view) {
        super(view);
    }

    @Override
    public void onNetRequest(@NonNull String topicId) {
//        NetRequest.single().request(GradeUrl.PAPER_TOPIC_DETAIL, NetRequest.single().getService().topicDetail(topicId, 0),
//                new SimpleRootViewNetListener<>(getView(), UIType.STATUS));
    }

    @Override
    public void onEnglishNetRequest(@NonNull String examGroupId, @NonNull String topicId) {
//        NetRequest.single().request(GradeUrl.PAPER_TOPIC_DETAIL, NetRequest.single().getService().englishTopicDetail(examGroupId, topicId),
//                new SimpleNetZipSuccessListener<BaseEntity<PaperEnglishTopicDetailEntity>>(getView()) {
//                    @Override
//                    public void onNetWorkSuccess(BaseEntity<PaperEnglishTopicDetailEntity> data) {
//                        if (getView() == null) {
//                            return;
//                        }
//                        getView().onEnglishSuccess(data.getData());
//                    }
//
//                    @Override
//                    public void onNetWorkComplete() {
//                    }
//                }.setHasStatus(true));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        NetRequest.single().cancel(GradeUrl.PAPER_TOPIC_DETAIL);
    }
}
