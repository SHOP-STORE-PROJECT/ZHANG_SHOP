package shop.yunifang.com.yunifang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;

import shop.yunifang.com.yunifang.R;
import shop.yunifang.com.yunifang.bean.SubBean;

/**
 * Created by ZhangFanfan on 2016/12/13.
 */
//网页页跳转详情界面
public class WebActivity extends Activity {

    private int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.web_view_layout);
        intentDatas();
    }

    private void intentDatas() {
        Intent intent = getIntent();
        ArrayList<SubBean.Ad5Bean> ad5Been =  (ArrayList<SubBean.Ad5Bean>) intent.getSerializableExtra("ad5bean");
        String   headerWebUrl = intent.getStringExtra("header");
        if(!TextUtils.isEmpty(headerWebUrl)) {
            initViews(headerWebUrl);
        }else if(ad5Been.size()>1) {
            year = intent.getIntExtra("year", 0);
            initViews(ad5Been.get(year).ad_type_dynamic_data);
        }
    }
    private void initViews(String webUrl) {
        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.loadUrl(webUrl);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
    }
}
