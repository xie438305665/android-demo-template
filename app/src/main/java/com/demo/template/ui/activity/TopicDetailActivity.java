package com.demo.template.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.webkit.WebSettings;

import com.android.library.bridge.annotation.TopicType;
import com.android.library.bridge.core.MVPActivity;
import com.android.library.net.entity.template.PaperEnglishTopicDetailEntity;
import com.android.library.net.entity.template.TopicDetailEntity;
import com.android.library.widget.custom.CustomWebView;
import com.demo.template.R;
import com.demo.template.annotation.BundleKey;
import com.demo.template.mvp.presenter.impl.TopicDetailPresenterImpl;
import com.demo.template.mvp.view.TopicDetailView;
import com.demo.template.utils.HtmlUtils;
import com.status.layout.Status;

import butterknife.BindView;

/**
 * @author xcl
 * 阅卷试题详情
 */
public class TopicDetailActivity extends MVPActivity<TopicDetailPresenterImpl, TopicDetailEntity>
        implements TopicDetailView {

    @BindView(R.id.webView)
    CustomWebView mWebView;
    private String topicId;
    private String maxScore;
    private String topicIndex;
    private String examGroupId;
    private int subjectId;

    @Override
    protected void initCreate(@Nullable Bundle savedInstanceState) {
        mCustomToolBar.setCenterTvText(R.string.grade_fill_score_detail_title);
        topicId = bundle.getString(BundleKey.TOPIC_ID, "0");
        maxScore = bundle.getString(BundleKey.MAX_SCORE, "0");
        topicIndex = bundle.getString(BundleKey.TOPIC_INDEX, "0");
        examGroupId = bundle.getString(BundleKey.EXAM_GROUP_ID, "0");
        subjectId = bundle.getInt(BundleKey.SUBJECT_ID, 0);
        mWebView.openProgress();
        if (subjectId == TopicType.ENGLISH) {
            WebSettings settings = mWebView.getSettings();
            settings.setUseWideViewPort(true);
            settings.setSupportZoom(true);
            settings.setBuiltInZoomControls(true);
            settings.setDisplayZoomControls(false);
            mPresenter.onEnglishNetRequest(examGroupId, topicId);
        } else {
            mPresenter.onNetRequest(topicId);
        }
        mWebView.setVerticalScrollBarEnabled(false);
    }

    @Override
    protected TopicDetailPresenterImpl initPresenter() {
        return new TopicDetailPresenterImpl(this);
    }

    @Override
    protected void onStatusRetry() {
        super.onStatusRetry();
        if (subjectId == TopicType.ENGLISH) {
            mPresenter.onEnglishNetRequest(examGroupId, topicId);
        } else {
            mPresenter.onNetRequest(topicId);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_paper_topic_detail;
    }

    @Override
    protected boolean showToolBar() {
        return true;
    }

    @Override
    public void onViewSuccess(TopicDetailEntity topicDetailEntity) {
        super.onViewSuccess(topicDetailEntity);
        mWebView.loadDataUrl(HtmlUtils.getPaperTopicDetailHtml(topicDetailEntity, maxScore, topicIndex));
    }

    @Override
    public void onEnglishSuccess(PaperEnglishTopicDetailEntity data) {
        if (TextUtils.isEmpty(data.getTitle())) {
            onChangeRootUI(Status.EMPTY);
            return;
        }
        onChangeRootUI(Status.SUCCESS);
        mWebView.loadDataUrl(HtmlUtils.getEnglishPaperTopicDetailHtml(data));
    }

    @Override
    protected void onDestroy() {
        mWebView.reset();
        super.onDestroy();
    }
}
