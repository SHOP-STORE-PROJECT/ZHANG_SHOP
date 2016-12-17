package shop.yunifang.com.yunifang.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;

import shop.yunifang.com.yunifang.R;
import shop.yunifang.com.yunifang.bean.Goods;

/**
 * Created by ZhangFanfan on 2016/12/16.
 */

public class ProFragment extends Fragment {
    private Context context;
    private WebView webView1;
    private WebView webView2;
    private WebView webView3;
public static Fragment newGetInstance(ArrayList<Goods.GoodsActivity> urlList){
    ProFragment proFragment = new ProFragment();
    Bundle bundle = new Bundle();
    bundle.putParcelableArrayList("URL",urlList);
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

        ArrayList<Goods.GoodsActivity> url = bundle.getParcelableArrayList("URL");
        //网络网址加载
        showUrl(webView1,url.get(0).description);
        showUrl(webView2,url.get(1).description);
        showUrl(webView3,url.get(2).description);  Log.e("Bundle--",url.get(0).description);

    }
    private void showUrl(WebView webView,String url) {
        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
    }
}
