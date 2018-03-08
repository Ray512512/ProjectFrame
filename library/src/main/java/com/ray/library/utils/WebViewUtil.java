package com.ray.library.utils;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Ray on 2017/12/29.
 */

public class WebViewUtil {

    public static void setWebViewSetting(WebView webView){
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        WebSettings msetting = webView.getSettings();
        msetting.setJavaScriptEnabled(true);
//        msetting.setAllowFileAccessFromFileURLs(true);
//        msetting.setAllowUniversalAccessFromFileURLs(true);
    }
}
