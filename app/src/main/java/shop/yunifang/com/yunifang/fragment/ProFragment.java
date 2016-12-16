package shop.yunifang.com.yunifang.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import shop.yunifang.com.yunifang.R;

/**
 * Created by ZhangFanfan on 2016/12/16.
 */

public class ProFragment extends Fragment {
    private Context context;
    private WebView webView1;
    private WebView webView2;
    private WebView webView3;
public static Fragment newGetInstance(String[] urlList){
    ProFragment proFragment = new ProFragment();
    Bundle bundle = new Bundle();
    bundle.putStringArray("URL",urlList);
    proFragment.setArguments(bundle);
    return proFragment;
}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     context = getActivity();
       View view = View.inflate(context, R.layout.product_layout,null);
        initViews(view);
        return view;
    }
    private void initViews(View view ) {
        webView1 = (WebView) view.findViewById(R.id.web_view1);
        webView2 = (WebView) view.findViewById(R.id.web_view2);
        webView3 = (WebView)view. findViewById(R.id.web_view3);
        //Bundle取值加载网络数据
        Bundle bundle = getArguments();
        String[] url = bundle.getStringArray("URL");
        //网络网址加载
        showUrl(webView1,url[0]);
        showUrl(webView2,url[1]);
        showUrl(webView3,url[2]);
    }
    private void showUrl(WebView webView,String url) {
        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
    }
}
