package com.android.library.widget.helper;

import android.app.Activity;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;

import com.android.library.widget.custom.CustomWebView;

public class HtmlHeightInterface {

    public static final String URL = "javascript:( function () { var h = document.body.clientHeight; window.height.height(h); } ) ()";

    private CustomWebView webView;
    private Activity activity;

    public HtmlHeightInterface(CustomWebView webView, Activity activity) {
        this.webView = webView;
        this.activity = activity;
    }

    @JavascriptInterface
    public void height(int height) {
        ViewGroup.LayoutParams layoutParams = webView.getLayoutParams();
        layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height, webView.getContext().getResources().getDisplayMetrics());
        activity.runOnUiThread(() -> webView.setLayoutParams(layoutParams));
    }

}
