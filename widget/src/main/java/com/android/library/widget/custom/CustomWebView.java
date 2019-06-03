package com.android.library.widget.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * @author xcl
 */
public class CustomWebView extends WebView {
    private ProgressBar progressbar;

    public CustomWebView(Context context) {
        super(context);
        init();
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        setOnLongClickListener(v -> true);
    }

    public void openProgress() {
        progressbar = new ProgressBar(getContext(), null, android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 10));
        addView(progressbar);
        setWebChromeClient(
                new WebChromeClient() {
                    @Override
                    public void onProgressChanged(WebView view, int newProgress) {
                        super.onProgressChanged(view, newProgress);
                        newProgressBar(newProgress);
                    }
                });
    }

    private void newProgressBar(int newProgress) {
        if (progressbar == null) {
            return;
        }
        if (newProgress == 100) {
            progressbar.setVisibility(GONE);
        } else {
            if (progressbar.getVisibility() == GONE) {
                progressbar.setVisibility(VISIBLE);
            }
            progressbar.setProgress(newProgress);
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (progressbar != null) {
            progressbar.setLayoutParams(progressbar.getLayoutParams());
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public void loadDataUrl(String url) {
        loadDataWithBaseURL(null, url, "text/html", "utf-8", null);
    }

    public void reset() {
        loadDataUrl("");
        clearHistory();
        removeAllViews();
        ((ViewGroup) getParent()).removeView(this);
        destroy();
    }
}
