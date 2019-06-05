package com.demo.template.mvp.presenter;

import android.support.annotation.NonNull;

import com.android.library.bridge.core.base.IPresenter;

public interface PaperTopicDetailPresenter extends IPresenter {
    void onNetRequest(@NonNull String topicId);

    void onEnglishNetRequest(@NonNull String examGroupId, @NonNull String topicId);
}
