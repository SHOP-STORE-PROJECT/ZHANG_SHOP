package shop.yunifang.com.yunifang.modle;

import com.android.volley.VolleyError;

/**
 * Created by ZhangFanfan on 2016/12/6.
 */
//回调数据接口
public interface CallBack {
    public void httpSuccess(String response);
    public void httpFailed(VolleyError error);
}
