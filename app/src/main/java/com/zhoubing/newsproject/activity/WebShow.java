package com.zhoubing.newsproject.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Xml;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.zhoubing.newsproject.R;

public class WebShow extends AppCompatActivity {

    private WebView webView;
    private ProgressBar progressBar;
    private final static String TAG = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_web_show);
        Intent intent = getIntent();
        String str = intent.getStringExtra("shuju");
        String geshi = intent.getStringExtra("geshi");
        webView = (WebView) findViewById(R.id.webview_show);
        progressBar = (ProgressBar) findViewById(R.id.pb);
        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        webView.getSettings().setDefaultTextEncodingName("UTF -8");//设置默认为utf-8
        webView.setWebViewClient(new MyWebViewClient());
        webView.setWebChromeClient(new MyWebChromeClient());
        if(TAG.equals(geshi)){
            webView.loadUrl(str);
        }else {
            webView.loadData(str, "text/html; charset=UTF-8", null);
        }



    }

    public class MyWebChromeClient extends WebChromeClient{

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            progressBar.setProgress(newProgress);
            if(newProgress == 100){
                progressBar.setVisibility(View.GONE);
            }
            super.onProgressChanged(view, newProgress);
        }
    }


    public class MyWebViewClient extends WebViewClient{

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(KeyEvent.KEYCODE_BACK == keyCode && webView.canGoBack()){
            webView.goBack();
        }
        return super.onKeyDown(keyCode, event);
    }
}
