package shop.yunifang.com.yunifang.modle;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by ZhangFanfan on 2016/12/6.
 * <p>
 * 通过volley搭建网络请求框架
 */

public class HttpUtils {

    private static RequestQueue mQueue;
    private static HttpUtils httpUtils;
//HttpUtils判空初始化
    public static HttpUtils netRequest(Context context) {
        if (httpUtils == null) {
            synchronized (HttpUtils.class) {
                if (httpUtils == null) {
                    httpUtils = new HttpUtils(context);
                }
            }
        }
        return httpUtils;
    }
//volley网络请求初始化
    private HttpUtils(Context context) {
        mQueue = Volley.newRequestQueue(context);
    }

    public void volleyRequest(String url, final CallBack callBack) {

        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                      callBack.httpSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               callBack.httpFailed(error);
            }
        });
        mQueue.add(stringRequest);
    }
}
