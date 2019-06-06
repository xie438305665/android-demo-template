package com.demo.template.mvp.view;


import com.android.library.bridge.core.base.IView;
import com.android.library.net.entity.template.PaperEnglishTopicDetailEntity;
import com.android.library.net.entity.template.TopicDetailEntity;

public interface TopicDetailView extends IView<TopicDetailEntity> {
    /**
     * 英语试题请求成功回调
     *
     * @param data 数据
     */
    void onEnglishSuccess(PaperEnglishTopicDetailEntity data);
}
