package com.demo.template.ui.activity;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.library.bridge.core.MVPActivity;
import com.android.library.bridge.util.HtmlUtils;
import com.android.library.widget.custom.CustomWebView;
import com.demo.template.R;
import com.demo.template.mvp.presenter.impl.WebViewTemplatePresenterImpl;
import com.demo.template.mvp.view.WebViewTemplateView;
import com.hjq.toast.ToastUtils;
import com.status.layout.Status;

import butterknife.BindView;

/**
 * @author xcl
 */
public class WebViewTemplateActivity extends MVPActivity<WebViewTemplatePresenterImpl, Object>
        implements WebViewTemplateView, View.OnKeyListener {

    @BindView(R.id.webView)
    CustomWebView mWebView;
    private String topicId;
    private boolean isError = false;

    @Override
    protected void initCreate(@Nullable Bundle savedInstanceState) {
        mCustomToolBar.setCenterTvText(R.string.app_name);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setUseWideViewPort(false);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.setWebViewClient(new WebViewListener());
        mWebView.setOnKeyListener(this);
        onStatusRetry();
    }

    @Override
    protected WebViewTemplatePresenterImpl initPresenter() {
        return new WebViewTemplatePresenterImpl(this);
    }

    @Override
    protected void onStatusRetry() {
        super.onStatusRetry();
        mPresenter.onNetRequest(topicId);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected boolean showToolBar() {
        return true;
    }

    @Override
    public void onViewSuccess(Object obj) {
        super.onViewSuccess(obj);
        mWebView.loadDataUrl(HtmlUtils.getHtml((CharSequence) obj));
    }

    @Override
    protected void onDestroy() {
        mWebView.reset();
        super.onDestroy();
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return false;
    }

    class WebViewListener extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            onChangeRootUI(Status.LOADING);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (!isError) {
                onChangeRootUI(Status.SUCCESS);
            }
        }


        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return;
            }
            onChangeRootUI(Status.ERROR);
            isError = true;
            ToastUtils.show("(错误码:" + errorCode + "  " + description + ")");
        }

        @TargetApi(Build.VERSION_CODES.M)
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            if (request.isForMainFrame()) {
                onChangeRootUI(Status.ERROR);
                ToastUtils.show("(错误码:" + error.getErrorCode() + "  " + error.getDescription().toString() + ")");
                isError = true;
            }
        }
    }
}
