package shop.yunifang.com.yunifang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import shop.yunifang.com.yunifang.R;

/**
 * Created by ZhangFanfan on 2016/12/13.
 */
//网页页跳转详情界面
public class WebActivity extends Activity {

    private String headerWebUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.web_view_layout);
        intentDatas();

        initViews(headerWebUrl);
    }

    private void intentDatas() {
        Intent intent = getIntent();
        headerWebUrl = intent.getStringExtra("header");
    }

    private void initViews(String webUrl) {
        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.loadUrl(webUrl);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
    }
}
